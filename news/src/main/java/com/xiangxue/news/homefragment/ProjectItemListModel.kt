package com.xiangxue.news.homefragment

import android.util.Log
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.news.homefragment.api.NewsListBean
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.apiresponse.NetworkResponse
import com.xiangxue.network.TecentNetworkWithoutEnvelopeApi
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.news.homefragment.api.NewsApiInterface
import com.xiangxue.news.homefragment.api.ProjectItemBean
import com.xiangxue.news.homefragment.api.ProjectServe
import com.xiangxue.news.homefragment.newslist.composables.titlecomposable.TitleComposableModel
import com.xiangxue.news.homefragment.newslist.composables.titlepicturecomposable.TitlePictureComposableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
class ProjectItemListModel(
    viewModelScope: CoroutineScope,
    private val channelId: String?,
    private val channelName: String?,
    iBaseModelListener: IBaseModelListener<List<IBaseComposableModel>>
) : BaseMvvmModel<ProjectItemBean, List<IBaseComposableModel>>(
    viewModelScope,
    iBaseModelListener,
    true,
    mCachedPreferenceKey = "project_item_list_model_${channelId}"
//    mCachedPreferenceKey = null
) {

    override suspend fun load() {
        val newsListBean = WanAndroidNetworkWithoutEnvelopeApi.getService(
            ProjectServe::class.java
        ).projectItemList(mPageNumber.toString(), channelId ?: "", "15")
        when (newsListBean) {
            is NetworkResponse.Success -> {
                onDataTransform(newsListBean.body, false)
            }
            is NetworkResponse.ApiError -> {
                onFailure(newsListBean.body.toString())
            }
            is NetworkResponse.NetworkError -> {
                onFailure(newsListBean.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(newsListBean.error?.message)
            }
        }
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }


    override fun onDataTransform(t: ProjectItemBean, isFromCache: Boolean) {
        if (isFromCache) return
        val baseViewModels: ArrayList<IBaseComposableModel> = ArrayList<IBaseComposableModel>()
        for (source in (t)!!.datas!!) {
            val viewModel =
                TitlePictureComposableModel(
                    source.title ?: "",
                    source.envelopePic ?: "",
                    source.id ?: "",
                    source.title ?: "",
                    source.link ?: "",
                    source.niceShareDate ?: "",
                    source.author ?: "",
                    source.desc ?: ""
                )
            baseViewModels.add(viewModel)
        }
        notifyResultsToListener(t, baseViewModels, isFromCache)
        Log.d("onDataTransform", "notifyResultsToListener(t, baseViewModels, isFromCache)")
    }
}