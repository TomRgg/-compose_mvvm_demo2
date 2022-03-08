package com.xiangxue.base.mvvm.model

interface MvvmDataTransformer<NETWORK_DATA> {
    fun onDataTransform(t: NETWORK_DATA, isFromCache: Boolean)
    fun onFailure(e: String?)
}