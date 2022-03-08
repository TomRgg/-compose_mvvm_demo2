package com.cmbgold.system.model

import android.util.Log
import com.cmbgold.system.bean.SystemItemComposeModel
import com.cmbgold.system.serve.SystemServe
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import kotlinx.coroutines.CoroutineScope

class SystemSecondListModel(
    viewModelStore: CoroutineScope,
    private val cid: String,
    iBaseModelListener: IBaseModelListener<List<IBaseComposableModel>>
) : BaseMvvmModel<List<SystemItemComposeModel>, List<IBaseComposableModel>>(
    viewModelScope = viewModelStore,
    iBaseModelListener = iBaseModelListener, true, mCachedPreferenceKey = null
) {

    override suspend fun load() {
        Log.d("SystemSecondListModel", "mPageNumber:$mPageNumber,cid:$cid")
        val data = WanAndroidNetworkWithoutEnvelopeApi.getService(SystemServe::class.java)
            .getSystemChildrenList(mPageNumber.toString(), cid, "20")
        when (data) {
            is NetworkResponse.Success -> {
                onDataTransform(data.body.datas, false)
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

    override fun onDataTransform(t: List<SystemItemComposeModel>, isFromCache: Boolean) {
        if (mPageNumber >= 1 && t.isEmpty()) {
            return
        }
        val list = ArrayList<IBaseComposableModel>()
        list.addAll(t)
        notifyResultsToListener(t, list, isFromCache)
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }

}