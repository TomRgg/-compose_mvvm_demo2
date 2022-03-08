package com.xiangxue.base.mvvm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.xiangxue.base.preference.BasicDataPreferenceUtil
import com.xiangxue.base.utils.MoshiUtils

@JsonClass(generateAdapter = true)
class CacheData {
    @Json(name = "networkData")
    var networkData: Any? = null

    @Json(name = "updateTimeInMillis")
    var updateTimeInMillis: Long = 0

    @Json(name = "resultData")
    var resultData: Any? = null

    companion object {
        fun saveDataToPreference(cachedPreferenceKey: String?, data: Any?, resultData: Any?) {
            if (data != null) {
                val cachedData = CacheData()
                cachedData.networkData = data
                cachedData.resultData = resultData
                cachedData.updateTimeInMillis = System.currentTimeMillis()
                BasicDataPreferenceUtil.getInstance()
                    .setString(cachedPreferenceKey, MoshiUtils.toJson(cachedData))
            }
        }
        fun isSameAsCached(cachedPreferenceKey: String?, resultData: Any?): Boolean {
            if (cachedPreferenceKey != null && resultData != null) {
                val cachedData: CacheData? = MoshiUtils.fromJson(
                    BasicDataPreferenceUtil.getInstance().getString(cachedPreferenceKey),
                    CacheData::class.java
                )
                if (cachedData != null) {
                    if (resultData != null && cachedData.getResultDataString() == MoshiUtils.toJson(
                            resultData
                        )
                    ) {
                        return true
                    }
                }
            }
            return false
        }
    }

    fun getNetworkDataString(): String? {
        return if (networkData != null) {
            MoshiUtils.toJson(networkData)
        } else ""
    }

    fun getResultDataString(): String {
        return if (resultData != null) {
            MoshiUtils.toJson(resultData)
        } else ""
    }
}