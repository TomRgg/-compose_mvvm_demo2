package com.xiangxue.news.homefragment.newslist

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class NewsListViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<NewsListModel, IBaseComposableModel>(savedStateHandle, true) {

    override fun createModel(): NewsListModel {
        return NewsListModel(
            viewModelScope,
            savedStateHandle?.get(BUNDLE_KEY_PARAM_CHANNEL_ID)!!,
            savedStateHandle?.get(BUNDLE_KEY_PARAM_CHANNEL_NAME)!!,
            NewsListViewModel@ this as IBaseModelListener<List<IBaseComposableModel>>
        )
    }
}