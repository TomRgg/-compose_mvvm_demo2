package com.cmbgold.profile.compose.activity.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cmbgold.profile.compose.activity.model.LogoutModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.model.PagingResult
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class LogoutViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<LogoutModel, String>(savedStateHandle, false) {

    private var success: (() -> Unit)? = null

    private var fail: ((String) -> Unit)? = null

    override fun createModel(): LogoutModel {
        return LogoutModel(viewModelScope, this as IBaseModelListener<List<String>>)
    }

    fun logout(success: () -> Unit, fail: (String) -> Unit) {
        this.success = success
        this.fail = fail
        tryToRefresh()
    }

    override fun onLoadSuccess(
        model: BaseMvvmModel<*, *>,
        data: List<String>?,
        pageResult: PagingResult?
    ) {
        super.onLoadSuccess(model, data, pageResult)
        this.success?.invoke()
    }

    override fun onLoadFail(
        model: BaseMvvmModel<*, *>,
        prompt: String?,
        pageResult: PagingResult?
    ) {
        super.onLoadFail(model, prompt, pageResult)
        this.fail?.invoke(prompt ?: "")
    }
}