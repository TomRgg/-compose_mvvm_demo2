package com.cmbgold.market.serve

import com.cmbgold.market.entity.MarketListBean
import com.xiangxue.network.apiresponse.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketListServe {

    @GET("https://wanandroid.com/user_article/list/{page}/json")
    suspend fun getMarketList(
        @Path("page")
        page: String,
        @Query("page_size") page_size: String

    ): NetworkResponse<MarketListBean>
}