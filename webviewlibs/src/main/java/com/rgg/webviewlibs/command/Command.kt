package com.rgg.webviewlibs.command

import com.rgg.webviewlibs.ICallbackFromMainProcessToWebViewProcessInterface

/**
 * 所有进程间通讯都需要继承该接口
 */
interface Command {
    fun getName(): String
    fun execute(param: Map<*, *>?, callback: ICallbackFromMainProcessToWebViewProcessInterface?)
}