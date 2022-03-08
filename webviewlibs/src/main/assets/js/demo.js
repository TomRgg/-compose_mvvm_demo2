var demo_js = {}
demo_js.os = {}
demo_js.os.isIOS = /IOS|iPhone|iPad/i.test(navigator.userAgent);
demo_js.os.isAndroid = !demo_js.os.isIOS;
demo_js.callbacks = {}

demo_js.takeNativeAction = function(command_name, parameters){
    console.log("demo_js takeNativeAction")
    var request = {};
    // 为了保持ios和android的一致性，所以将请求封装成一个String
    request.name = command_name;
    request.param = parameters;
    if(window.demo_js.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.web_view_interface.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.web_view_interface.postMessage(JSON.stringify(request))
    }
}

demo_js.takeNativeActionWithCallback = function(command_name, parameters, callback) {
    var callback_name = "native_to_js_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    demo_js.callbacks[callback_name] = callback;

    var request = {};
    request.name = command_name;
    request.param = parameters;
    request.param.callback_name = callback_name;
    if(window.demo_js.os.isAndroid){
        window.web_view_interface.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.web_view_interface.postMessage(JSON.stringify(request))
    }
}

demo_js.callback = function (callback_name, response) {
   var callback_object = demo_js.callbacks[callback_name];
   if (callback_object !== undefined){
       var ret = callback_object(response);
       if(ret === false){
           return
       }
       delete demo_js.callbacks[callback_name];
   }
}