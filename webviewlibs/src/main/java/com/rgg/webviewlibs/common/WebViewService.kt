package com.rgg.webviewlibs.common

import android.content.Context
import com.rgg.webviewlibs.base.WebViewFragment
import com.rgg.webviewlibs.entity.WebEntity

interface WebViewService {
    fun startWebViewActivity(entity: WebEntity, context: Context)

    fun getWebViewFragment(entity: WebEntity, context: Context): WebViewFragment
}