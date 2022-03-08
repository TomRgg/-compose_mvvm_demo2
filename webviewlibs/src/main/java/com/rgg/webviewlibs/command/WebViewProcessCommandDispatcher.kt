package com.rgg.webviewlibs.command

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.rgg.webviewlibs.ICallbackFromMainProcessToWebViewProcessInterface
import com.rgg.webviewlibs.IWebViewProcessToMainProcessInterface
import com.rgg.webviewlibs.base.BaseWebView
import com.rgg.webviewlibs.mainprocess.MainProcessCommandService
import java.util.*

object WebViewProcessCommandDispatcher : ServiceConnection {

    const val TAG = "WebViewProcessCommandDispatcher"

    private var iWebViewProcessToMainProcessInterface: IWebViewProcessToMainProcessInterface? = null
    private var application: Application? = null

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iWebViewProcessToMainProcessInterface =
            IWebViewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iWebViewProcessToMainProcessInterface = null
        application?.run {
            initAidlConnection(this)
        }
    }

    fun initAidlConnection(application: Application) {
        Intent(application, MainProcessCommandService::class.java).run {
            application.bindService(
                this,
                this@WebViewProcessCommandDispatcher,
                Context.BIND_AUTO_CREATE
            )
            this@WebViewProcessCommandDispatcher.application = application
        }
    }

    fun executeCommand(commandName: String, jsonParams: String, webView: BaseWebView?) {
        iWebViewProcessToMainProcessInterface?.run {
            handleWebCommand(commandName,
                jsonParams, object : ICallbackFromMainProcessToWebViewProcessInterface.Stub() {
                    @SuppressLint("LongLogTag")
                    override fun onResult(toJavascriptCallbackName: String?, response: String?) {
                        Log.d(
                            TAG,
                            "executeCommand, ICallbackFromMainProcessToWebViewProcessInterface"
                        )
                        webView?.handleCallback(toJavascriptCallbackName, response)
                    }
                })
        }
    }

    fun executeCommandNative(commandName: String, param: Map<String, String>) {
        iWebViewProcessToMainProcessInterface?.run {
            val jsonParams = param.toString()
            handleWebCommand(commandName, jsonParams, object :
                ICallbackFromMainProcessToWebViewProcessInterface.Stub() {
                override fun onResult(toJavascriptCallbackName: String?, response: String?) {

                }
            })
        }
    }
}