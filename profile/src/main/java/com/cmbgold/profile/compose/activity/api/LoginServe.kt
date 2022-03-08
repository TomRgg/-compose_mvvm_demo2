package com.cmbgold.profile.compose.activity.api

import com.cmbgold.profile.compose.activity.entity.LoginBean
import com.xiangxue.network.apiresponse.NetworkResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginServe {

    @FormUrlEncoded
    @POST("https://www.wanandroid.com/user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): NetworkResponse<LoginBean>

    @GET("https://www.wanandroid.com/user/logout/json")
    suspend fun logout ():NetworkResponse<String>
}