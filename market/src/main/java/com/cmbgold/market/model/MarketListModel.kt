package com.cmbgold.market.model

import com.cmbgold.market.entity.MarketListBean
import com.cmbgold.market.serve.MarketListServe
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import kotlinx.coroutines.CoroutineScope

class MarketListModel(
    viewModelStore: CoroutineScope,
    iBaseModelListener: IBaseModelListener<List<IBaseComposableModel>>
) : BaseMvvmModel<MarketListBean, List<IBaseComposableModel>>(
    viewModelStore,
    iBaseModelListener,
    true,
    mCachedPreferenceKey = "market_list"
) {

    override suspend fun load() {
        val data = WanAndroidNetworkWithoutEnvelopeApi.getService(MarketListServe::class.java)
            .getMarketList( mPageNumber.toString(),"10")
//            .getMarketList()

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

    override fun onDataTransform(t: MarketListBean, isFromCache: Boolean) {
        val list = ArrayList<IBaseComposableModel>()
        t?.data?.let {
            list.addAll(it)
            notifyResultsToListener(t, list, isFromCache)
        }
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }
}