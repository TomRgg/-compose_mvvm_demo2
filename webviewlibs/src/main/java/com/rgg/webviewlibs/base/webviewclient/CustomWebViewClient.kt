package com.rgg.webviewlibs.base.webviewclient

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.util.Log
import android.webkit.*
import androidx.annotation.RequiresApi

class CustomWebViewClient(private var mWebViewCallBack: WebViewCallBack?) : WebViewClient() {

    /**
     * url重定向
     */
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        url?.let {
            return if (it.startsWith("http") || it.startsWith("https")) {
                view?.loadUrl(it)
                false
            } else {
                true
            }
        }
        return false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.i(TAG, "onPageStarted:  url: $url .....  favicon: $favicon")
        mWebViewCallBack?.pageStarted(url)
            ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Log.i(TAG, "onPageFinished:  url: $url .....")

        mWebViewCallBack?.pageFinished(url)
            ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        Log.i(
            TAG,
            "onReceivedError:  request: ${request?.method} ..... error: " +
                    "${error?.description.toString()}....errorCode: ${error?.errorCode}..."
        )
        super.onReceivedError(view, request, error)
        mWebViewCallBack?.onError()
            ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    companion object {
        private const val TAG = "CustomWebViewClient"
    }
}