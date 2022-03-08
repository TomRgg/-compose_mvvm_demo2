package com.cmbgold.system.serve

import com.cmbgold.system.bean.SystemComposableModel
import com.cmbgold.system.bean.SystemListComposeModel
import com.xiangxue.network.apiresponse.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SystemServe {

    @GET("https://www.wanandroid.com/tree/json")
    suspend fun getSystemList(): NetworkResponse<List<SystemComposableModel>>


    @GET("https://www.wanandroid.com/article/list/{page}/json")
    suspend fun getSystemChildrenList(
        @Path("page")
        page: String,
        @Query("cid") cid: String,
        @Query("page_size") page_size: String

    ): NetworkResponse<SystemListComposeModel>
}