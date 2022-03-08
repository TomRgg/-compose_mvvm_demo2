package com.cmbgold.profile.compose.activity.model

import com.cmbgold.profile.compose.activity.api.LoginServe
import com.cmbgold.profile.compose.activity.entity.LoginBean
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import kotlinx.coroutines.CoroutineScope

class LogoutModel(
    coroutineScope: CoroutineScope,
    iBaseModelListener: IBaseModelListener<List<String>>
) :
    BaseMvvmModel<String, List<String>>(coroutineScope, iBaseModelListener) {
    override suspend fun load() {
        val data = WanAndroidNetworkWithoutEnvelopeApi.getService(LoginServe::class.java).logout()
        when (data) {
            is NetworkResponse.Success -> {
                onDataTransform("退出登录成功", false)
            }
            is NetworkResponse.ApiError -> {
                onFailure("退出登录失败")
            }
            is NetworkResponse.NetworkError -> {
                onFailure(data.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(data.error?.message)
            }
        }

    }

    override fun onDataTransform(t: String, isFromCache: Boolean) {
        val list = ArrayList<String>()
        list.add(t)
        notifyResultsToListener(t, list, false)
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }
}