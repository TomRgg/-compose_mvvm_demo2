package com.rgg.webviewlibs.impl

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.google.auto.service.AutoService
import com.rgg.webviewlibs.base.WebViewActivity
import com.rgg.webviewlibs.base.WebViewFragment
import com.rgg.webviewlibs.common.WebViewService
import com.rgg.webviewlibs.entity.WebEntity

@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService {
    override fun startWebViewActivity(entity: WebEntity, context: Context) {
        Intent().apply {
            setClass(context, WebViewActivity::class.java)
            putExtra(WebViewActivity.ENTITY, entity)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.run {
            context.startActivity(this)
        }
    }

    override fun getWebViewFragment(entity: WebEntity, context: Context): WebViewFragment {
        return WebViewFragment.newInstance(entity, context as FragmentActivity)
    }
}