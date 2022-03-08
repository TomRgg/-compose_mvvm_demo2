package com.cmbgold.system.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cmbgold.system.model.SystemListModel
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class SystemListViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<SystemListModel, IBaseComposableModel>
        (savedStateHandle) {

    override fun createModel(): SystemListModel {
        return SystemListModel(
            viewModelScope,
            this as IBaseModelListener<List<IBaseComposableModel>>
        )
    }
}