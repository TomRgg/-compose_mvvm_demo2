package com.xiangxue.network

import com.xiangxue.network.apiresponse.MoshiResultTypeAdapterFactory
import com.xiangxue.network.base.BaseNetworkApi
import com.xiangxue.network.utils.TecentUtil
import okhttp3.Interceptor

object WanAndroidNetworkWithoutEnvelopeApi : BaseNetworkApi() {

    override fun getInterceptor(): Interceptor? {
        return Interceptor { chain ->
            val timeStr = TecentUtil.getTimeStr()
            val builder = chain.request().newBuilder()
            builder.addHeader("Source", "source")
            builder.addHeader("Authorization", TecentUtil.getAuthorization(timeStr))
            builder.addHeader("Date", timeStr)
            chain.proceed(builder.build())
        }
    }

    override fun getEnvelopeHandler(): MoshiResultTypeAdapterFactory.Envelope? {
        return object : MoshiResultTypeAdapterFactory.Envelope {
            override fun getStatusCodeKey(): String {
                return "errorCode"
            }

            override fun getErrorMessageKey(): String {
                return "errorMsg"
            }

            override fun getDataKey(): String {
                return "data"
            }

            override fun doesStatusCodeIndicateSuccess(statusCode: Int): Boolean {
                return statusCode == 0
            }
        }
    }

    override fun getFormal(): String {
        return "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/"
    }

    override fun getTest(): String {
        return "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/"
    }
}