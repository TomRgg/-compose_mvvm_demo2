package com.xiangxue.news.homefragment.api

import com.xiangxue.network.apiresponse.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectServe {

    @GET("https://www.wanandroid.com/project/tree/json")
    suspend fun projectTab(): NetworkResponse<List<ProjectTabBean>>

    @GET("https://www.wanandroid.com/project/list/{pageNumber}/json")
    suspend fun projectItemList(
        @Path("pageNumber") pageNumber: String,
        @Query("cid") cid: String,
        @Query("page_size") page_size: String
    ): NetworkResponse<ProjectItemBean>

}