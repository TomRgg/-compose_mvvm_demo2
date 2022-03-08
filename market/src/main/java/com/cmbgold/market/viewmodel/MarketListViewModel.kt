package com.cmbgold.market.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cmbgold.market.model.MarketListModel
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.model.PagingResult
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class MarketListViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<MarketListModel, IBaseComposableModel>(
        savedStateHandle
    ) {
    override fun createModel(): MarketListModel {
        return MarketListModel(
            viewModelScope,
            this as IBaseModelListener<List<IBaseComposableModel>>
        )
    }
}