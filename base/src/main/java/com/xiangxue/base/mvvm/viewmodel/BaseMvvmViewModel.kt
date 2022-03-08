package com.xiangxue.base.mvvm.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.model.PagingResult

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
abstract class BaseMvvmViewModel<MODEL : BaseMvvmModel<*, *>?, DATA>(
    val savedStateHandle: SavedStateHandle?,
    var isPreLoad: Boolean = true
) :
    ViewModel(), LifecycleObserver,
    IBaseModelListener<List<DATA>?> {
    var model: MODEL? = null
    var dataList = mutableStateListOf<DATA>()
    var viewStatusLiveData = mutableStateOf(ViewStatus.LOADING)
    var errorMessage: String = ""

    fun tryToRefresh() {
        if (model == null) {
            model = createModel()
        }
        if (model != null) {
            model!!.refresh()
        }
    }

    fun tryToLoadNextPage() {
        if (model == null) {
            model = createModel()
        }
        model!!.loadNextPage()
    }

    override fun onLoadSuccess(
        model: BaseMvvmModel<*, *>,
        data: List<DATA>?,
        pageResult: PagingResult?
    ) {
        if (model === this.model) {
            if (model != null) {
                if (model.isPaging) {
                    if (pageResult?.isFirstPage == true && model.mPageNumber - 1 == model.initPagerNumber) {
                        dataList!!.clear()
                    }
                    if (pageResult?.isEmpty == true) {
                        if (pageResult?.isFirstPage == true) {
                            viewStatusLiveData.value = ViewStatus.EMPTY
                        } else {
                            viewStatusLiveData.value = ViewStatus.NO_MORE_DATA
                        }
                    } else {
                        if (data != null) {
                            dataList!!.addAll(data)
                        }
                        viewStatusLiveData.value = ViewStatus.SHOW_CONTENT
                    }
                } else {
//                    dataList!!.clear()
//                    if (data != null) {
//                        dataList!!.addAll(data)
//                    }
                    viewStatusLiveData.value = ViewStatus.SHOW_CONTENT
                }
            }
        }

        if (pageResult?.isEmpty == true || model.mPageNumber - 1 == model.initPagerNumber) {
            dataList.clear()
        }
        if (data?.isNotEmpty() == true) {
            dataList.addAll(data)
        }
    }

    override fun onLoadFail(
        model: BaseMvvmModel<*, *>,
        prompt: String?,
        pageResult: PagingResult?
    ) {
        errorMessage = prompt ?: ""
        if (model.isPaging && !pageResult?.isFirstPage!!) {
            viewStatusLiveData.value = ViewStatus.LOAD_MORE_FAILED
        } else {
            viewStatusLiveData.value = ViewStatus.REFRESH_ERROR
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        if (dataList == null || dataList!!.size == 0) {
            model!!.refresh()
        } else {
            viewStatusLiveData.value = viewStatusLiveData.value
        }
    }

    protected abstract fun createModel(): MODEL

    init {
        if (isPreLoad) {
            tryToRefresh()
        } else {
            if (model == null) {
                model = createModel()
            }
        }
    }
}