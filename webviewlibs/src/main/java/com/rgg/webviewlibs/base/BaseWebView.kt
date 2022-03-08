package com.rgg.webviewlibs.base

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.webkit.WebView
import com.rgg.webviewlibs.base.settings.WebViewDefaultSettings
import com.rgg.webviewlibs.base.webchromeclient.CustomWebChromeClient
import com.rgg.webviewlibs.base.webviewclient.CustomWebViewClient
import com.rgg.webviewlibs.base.webviewclient.WebViewCallBack
import com.rgg.webviewlibs.command.WebViewProcessCommandDispatcher

class BaseWebView : WebView {

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context!!, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        WebViewProcessCommandDispatcher.initAidlConnection(context.applicationContext as Application)
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewCallBack?.let {
            WebViewDefaultSettings.instance.setSettings(this)
            webViewClient = CustomWebViewClient(it)
            webChromeClient = CustomWebChromeClient(it)
            addJavascriptInterface(WebViewJavaScripInterface(this), "web_view_interface")
        }
    }

    fun handleCallback(callbackName: String?, response: String?) {
        if (callbackName?.isNotEmpty() == true && response?.isNotEmpty() == true) {
            Handler(Looper.getMainLooper()).post {
                val jsCode =
                    "javascript:demo_js.callback('$callbackName',$response)"
                evaluateJavascript(jsCode, null)
            }
        }
    }

    companion object {
        const val TAG = "BaseWebView"
    }
}