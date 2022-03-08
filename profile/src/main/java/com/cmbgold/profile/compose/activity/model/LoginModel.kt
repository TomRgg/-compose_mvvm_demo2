package com.cmbgold.profile.compose.activity.model

import com.cmbgold.profile.compose.activity.api.LoginServe
import com.cmbgold.profile.compose.activity.entity.LoginBean
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import kotlinx.coroutines.CoroutineScope

class LoginModel(
    coroutineScope: CoroutineScope,
    iBaseModelListener: IBaseModelListener<List<LoginBean>>
) : BaseMvvmModel<LoginBean, List<LoginBean>>(
    coroutineScope,
    iBaseModelListener,
    false

) {
    var username: String? = ""
    var password: String? = ""

    fun setLogin(n: String, p: String) {
        username = n
        password = p
//        mIsLoading = false
    }

    override suspend fun load() {
        val loginBean = WanAndroidNetworkWithoutEnvelopeApi.getService(LoginServe::class.java)
            .login(username = username ?: "", password = password ?: "")
        when (loginBean) {
            is NetworkResponse.Success -> {
                onDataTransform(loginBean.body, false)
            }
            is NetworkResponse.ApiError -> {
                onFailure(loginBean.body.toString())
            }
            is NetworkResponse.NetworkError -> {
                onFailure(loginBean.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(loginBean.error?.message)
            }
        }
    }

    override fun onDataTransform(t: LoginBean, isFromCache: Boolean) {
        val list = ArrayList<LoginBean>()
        list.add(t)

        //通知结果
        notifyResultsToListener(t, list, false)
    }

    override fun onFailure(e: String?) {
        e?.let {
            notifyFailureToListener(it)
        }
    }
}