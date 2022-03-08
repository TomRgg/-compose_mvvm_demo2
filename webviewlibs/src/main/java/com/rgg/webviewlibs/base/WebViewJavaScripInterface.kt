package com.rgg.webviewlibs.base

import android.util.Log
import android.webkit.JavascriptInterface
import com.rgg.webviewlibs.command.WebViewProcessCommandDispatcher
import org.json.JSONObject
import java.lang.Exception
import java.lang.ref.SoftReference

class WebViewJavaScripInterface {

    private var softReference: SoftReference<BaseWebView>? = null

    constructor(baseWebView: BaseWebView?) {
        baseWebView?.run {
            softReference = SoftReference(this)
        }
    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String) {
        try {
            JSONObject(jsParam)?.run {
                val commandName = get("name") as String
                val param = getJSONObject("param")
                Log.d(
                    BaseWebView.TAG,
                    "takeNativeAction, commandName: $commandName , param: $param"
                )
                WebViewProcessCommandDispatcher.executeCommand(
                    commandName = commandName,
                    jsonParams = param.toString(),
                    softReference?.get()
                )
            }

        } catch (e: Exception) {
            Log.d(BaseWebView.TAG, "Exception,${e.message}")
        }
    }
}