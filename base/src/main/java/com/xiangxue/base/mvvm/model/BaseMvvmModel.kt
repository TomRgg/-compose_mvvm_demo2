package com.xiangxue.base.mvvm.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.xiangxue.base.preference.BasicDataPreferenceUtil
import com.xiangxue.base.utils.GenericUtils
import com.xiangxue.base.utils.MoshiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseMvvmModel<NETWORK_DATA, RESULT_DATA>(
    private val viewModelScope: CoroutineScope,
    private val iBaseModelListener: IBaseModelListener<RESULT_DATA>,
    open val isPaging: Boolean = false,
    private val mCachedPreferenceKey: String? = null,
    private val apkPredefinedData: String? = null,
    val initPagerNumber: Int = 0
) : MvvmDataTransformer<NETWORK_DATA> {
    var mIsLoading = mutableStateOf(false)
    var mPageNumber = initPagerNumber

    open fun refresh() {
        if (!mIsLoading.value) {
            mIsLoading.value = true
            if (isPaging) {
                mPageNumber = initPagerNumber
            }
            if (mCachedPreferenceKey != null) {
                val cachedData: CacheData? = MoshiUtils.fromJson(
                    BasicDataPreferenceUtil.getInstance().getString(mCachedPreferenceKey),
                    CacheData::class.java
                )
                if (cachedData != null) {
                    val json = cachedData.getNetworkDataString() ?: ""
                    Log.d("BaseMvvmModel", "json:::$json")
                    val savedData: NETWORK_DATA? = MoshiUtils.fromJson(
                        json,
                        GenericUtils.getGenericType(this) as Class<NETWORK_DATA>
                    )
                    if (savedData != null) {
                        onDataTransform(savedData, true)
                    }
                    if (!isNeedToUpdate(cachedData.updateTimeInMillis)) {
                        return
                    }
                } else if (apkPredefinedData != null) {
                    val savedData: NETWORK_DATA? = MoshiUtils.fromJson(
                        apkPredefinedData,
                        GenericUtils.getGenericType(this) as Class<NETWORK_DATA>
                    )
                    if (savedData != null) {
                        onDataTransform(savedData, true)
                    }
                }
            }
            viewModelScope.launch {
                load()
            }
        }
    }

    open fun loadNextPage() {
        if (!mIsLoading.value) {
            mIsLoading.value = true
            viewModelScope.launch {
                load()
            }
        }
    }

    protected abstract suspend fun load()

    protected open fun isRefresh(): Boolean {
        return mPageNumber == initPagerNumber
    }

    protected open fun notifyResultsToListener(
        networkData: NETWORK_DATA, resultData: RESULT_DATA,
        isFromCache: Boolean
    ) {
        if (iBaseModelListener != null) {
            // notify
            if (isPaging) {
                iBaseModelListener.onLoadSuccess(
                    this,
                    resultData,
                    PagingResult(
                        if (resultData == null) true else (resultData as List<*>).isEmpty(),
                        isRefresh(),
                        if (resultData == null) false else (resultData as List<*>).size > 0
                    )
                )
                // save NetworkData to preference
                if (mCachedPreferenceKey != null && isRefresh() && !isFromCache) {
                    CacheData.saveDataToPreference(mCachedPreferenceKey, networkData, resultData)
                }
            } else {
                if (isFromCache || !CacheData.isSameAsCached(mCachedPreferenceKey, resultData)) {
                    iBaseModelListener.onLoadSuccess(this, resultData)
                }
                // save NetworkData to preference
                if (mCachedPreferenceKey != null && !isFromCache) {
                    CacheData.saveDataToPreference(mCachedPreferenceKey, networkData, resultData)
                }
            }

            // update page number
            if (isPaging) {
                if (resultData != null && (resultData as List<*>).size > 0) {
                    mPageNumber++
                }
            }
        }
        mIsLoading.value = false
    }

    protected open fun notifyFailureToListener(errorMessage: String?) {
        if (iBaseModelListener != null) {
            if (isPaging) {
                iBaseModelListener.onLoadFail(
                    this,
                    errorMessage,
                    PagingResult(true, isRefresh(), false)
                )
            } else {
                iBaseModelListener.onLoadFail(this, errorMessage)
            }
        }
        mIsLoading.value = false
    }

    protected open fun isNeedToUpdate(cachedTimeSlot: Long): Boolean {
        return true
    }
}