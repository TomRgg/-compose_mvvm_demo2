package com.xiangxue.base.mvvm.model

interface IBaseModelListener<RESULT_DATA> {
    fun onLoadSuccess(model: BaseMvvmModel<*, *>, data: RESULT_DATA,  pageResult: PagingResult? = null)
    fun onLoadFail(model: BaseMvvmModel<*, *>, prompt: String?,  pageResult: PagingResult? = null)
}