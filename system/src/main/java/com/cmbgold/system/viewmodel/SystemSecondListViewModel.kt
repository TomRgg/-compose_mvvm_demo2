package com.cmbgold.system.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cmbgold.system.BUNDLE_KEY_PARAM_ID
import com.cmbgold.system.model.SystemListModel
import com.cmbgold.system.model.SystemSecondListModel
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class SystemSecondListViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<SystemSecondListModel, IBaseComposableModel>
        (savedStateHandle) {

    override fun createModel(): SystemSecondListModel {
        return SystemSecondListModel(
            viewModelScope,
            savedStateHandle?.get(BUNDLE_KEY_PARAM_ID) ?: "",
            this as IBaseModelListener<List<IBaseComposableModel>>
        )
    }
}