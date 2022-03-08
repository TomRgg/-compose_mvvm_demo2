package com.rgg.webviewlibs.mainprocess

import android.annotation.SuppressLint
import android.util.Log
import com.rgg.webviewlibs.ICallbackFromMainProcessToWebViewProcessInterface
import com.rgg.webviewlibs.IWebViewProcessToMainProcessInterface
import com.rgg.webviewlibs.command.Command
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

@SuppressLint("LongLogTag")
object MainProcessCommandManager : IWebViewProcessToMainProcessInterface.Stub() {

    private const val TAG = "MainProcessCommandManager"
    private var mCommands: HashMap<String, Command> = HashMap()

    /**
     * 使用serviceLoader把有加上@AutoService的注解的command实现类 找出来，然后添加到map上
     */
    init {
        try {
            ServiceLoader.load(Command::class.java).run {
                for (command in this) {
                    if (!mCommands.containsKey(command.getName())) {
                        mCommands[command.getName()] = command
                    }
                }
            }

        } catch (e: Exception) {
            Log.e("MainProcessCommandManager", "初始化失败")
        }
    }

    @SuppressLint("LongLogTag")
    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainProcessToWebViewProcessInterface?
    ) {
        Log.d(
            "MainProcessCommandManager",
            "handleWebCommand, commandName: $commandName ,jsonParams: $jsonParams"
        )
        try {
            val jsonObject = JSONObject(jsonParams)
            val keyIterator: Iterator<String> = jsonObject.keys()
            var key: String
            var value: String
            var param = HashMap<String, String>()
            while (keyIterator.hasNext()) {
                key = keyIterator.next()
                value = if (jsonObject[key] is String) {
                    jsonObject[key] as String
                } else {
                    jsonObject[key].toString()
                }
                param[key] = value
            }
            mCommands[commandName]?.execute(param, callback)
        } catch (jsonException: JSONException) {
            throw JSONException("json字符串解析失败")
        } catch (e: Exception) {
            Log.e(
                "MainProcessCommandManager",
                "Exception: ${e.message}"
            )
        }
    }
}