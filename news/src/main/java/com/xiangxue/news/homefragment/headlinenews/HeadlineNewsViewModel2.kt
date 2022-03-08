package com.xiangxue.news.homefragment.headlinenews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel
import com.xiangxue.news.homefragment.api.ProjectTabBean

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
class HeadlineNewsViewModel2(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<ChannelsModel2, ProjectTabBean>(savedStateHandle) {
    override fun createModel(): ChannelsModel2 {
        return ChannelsModel2(
            viewModelScope,
            this as IBaseModelListener<List<ProjectTabBean>>
        )
    }
}