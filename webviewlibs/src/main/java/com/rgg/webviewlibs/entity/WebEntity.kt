package com.rgg.webviewlibs.entity

import java.io.Serializable

class WebEntity : Serializable {
    var isShowActionBar: Boolean? = true
    var url: String? = ""
    var isNativeRefresh: Boolean? = false
    var title: String? = ""

    //是否使用传递过来的title，而不获取html的
    var userNativeTitle: Boolean? = false
    //加载html字符串
    var html: String? = null
}
