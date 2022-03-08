package com.rgg.webviewlibs.base.webviewclient

interface WebViewCallBack {
    fun pageStarted(url: String?)
    fun pageFinished(url: String?)
    fun onError()
    fun updateTitle(title: String?)
    fun progress(progress: Int)
}