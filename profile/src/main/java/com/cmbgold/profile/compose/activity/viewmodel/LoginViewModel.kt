package com.cmbgold.profile.compose.activity.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cmbgold.profile.compose.activity.entity.LoginBean
import com.cmbgold.profile.compose.activity.model.LoginModel
import com.cmbgold.profile.compose.activity.model.ProfileModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.base.mvvm.model.PagingResult
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel

class LoginViewModel(savedStateHandle: SavedStateHandle) :
    BaseMvvmViewModel<LoginModel, LoginBean>(savedStateHandle, false) {

    private lateinit var loginModel: LoginModel

    private var callbackSuccess: ((LoginBean) -> Unit)? = null

    private var callbackFail: ((String) -> Unit)? = null

    override fun createModel(): LoginModel {
        loginModel = LoginModel(
            viewModelScope,
            this as IBaseModelListener<List<LoginBean>>
        )
        return loginModel
    }

    fun login(
        name: String,
        password: String,
        success: (LoginBean) -> Unit,
        fail: (String) -> Unit
    ) {
        loginModel?.setLogin(name, password)
        tryToRefresh()
        this.callbackSuccess = success
        this.callbackFail = fail
    }

    override fun onLoadSuccess(
        model: BaseMvvmModel<*, *>,
        data: List<LoginBean>?,
        pageResult: PagingResult?
    ) {
        super.onLoadSuccess(model, data, pageResult)
        if (dataList.size > 0) {
            ProfileModel.info = dataList[0]
            callbackSuccess?.invoke(dataList[0])
        }
    }

    override fun onLoadFail(
        model: BaseMvvmModel<*, *>,
        prompt: String?,
        pageResult: PagingResult?
    ) {
        super.onLoadFail(model, prompt, pageResult)
        callbackFail?.invoke(errorMessage)
    }
}