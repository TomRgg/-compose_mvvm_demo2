// IWebViewProcessToMainProcessInterface.aidl
package com.rgg.webviewlibs;

// Declare any non-default types here with import statements
import com.rgg.webviewlibs.ICallbackFromMainProcessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessInterface {

    void handleWebCommand(String commandName,
    String jsonParams,
    in ICallbackFromMainProcessToWebViewProcessInterface callback);
}