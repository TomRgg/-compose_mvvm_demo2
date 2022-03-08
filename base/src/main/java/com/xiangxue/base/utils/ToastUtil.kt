package com.xiangxue.base.utils

import android.app.Application
import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun showToast(context: Context, text: String) {
        Toast.makeText(context.applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
