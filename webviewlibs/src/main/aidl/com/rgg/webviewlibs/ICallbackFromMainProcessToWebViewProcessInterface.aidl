// ICallbackFromMainProcessToWebViewProcessInterface.aidl
package com.rgg.webviewlibs;

// Declare any non-default types here with import statements

interface ICallbackFromMainProcessToWebViewProcessInterface {
    void onResult(String toJavascriptCallbackName, String response);
}