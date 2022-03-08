package com.cmbgold.system.model

import com.cmbgold.system.bean.SystemComposableModel
import com.cmbgold.system.serve.SystemServe
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import kotlinx.coroutines.CoroutineScope

class SystemListModel(
    viewModelStore: CoroutineScope,
    iBaseModelListener: IBaseModelListener<List<IBaseComposableModel>>
) : BaseMvvmModel<List<SystemComposableModel>, List<IBaseComposableModel>>(
    viewModelScope = viewModelStore,
    iBaseModelListener = iBaseModelListener, false, mCachedPreferenceKey = null
) {

    override suspend fun load() {
        val data = WanAndroidNetworkWithoutEnvelopeApi.getService(SystemServe::class.java)
            .getSystemList()
        when (data) {
            is NetworkResponse.Success -> {
                onDataTransform(data.body, false)
            }
            is NetworkResponse.ApiError -> {
                onFailure(data.body.toString())
            }
            is NetworkResponse.NetworkError -> {
                onFailure(data.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(data.error?.message)
            }
        }

    }

    override fun onDataTransform(t: List<SystemComposableModel>, isFromCache: Boolean) {
        if (isFromCache) return
        val list = ArrayList<IBaseComposableModel>()
        list.addAll(t)
        notifyResultsToListener(t, list, false)
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }

}