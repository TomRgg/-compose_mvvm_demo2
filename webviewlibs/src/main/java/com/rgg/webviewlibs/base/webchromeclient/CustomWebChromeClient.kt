package com.rgg.webviewlibs.base.webchromeclient

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.rgg.webviewlibs.base.webviewclient.WebViewCallBack

class CustomWebChromeClient(private var webViewCallBack: WebViewCallBack?) : WebChromeClient() {

    override fun onReceivedTitle(view: WebView, title: String) {
        webViewCallBack?.run {
            updateTitle(title)
        }
    }

    /**
     * Report a JavaScript console message to the host application. The ChromeClient
     * should override this to process the log message as they see fit.
     * @param consoleMessage Object containing details of the console message.
     * @return `true` if the message is handled by the client.
     */
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        // Call the old version of this function for backwards compatability.
        Log.d(TAG, consoleMessage.message())
        return super.onConsoleMessage(consoleMessage)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        webViewCallBack?.run {
            progress(newProgress)
        }
    }

    companion object {
        private const val TAG = "CustomWebChromeClient"
    }
}