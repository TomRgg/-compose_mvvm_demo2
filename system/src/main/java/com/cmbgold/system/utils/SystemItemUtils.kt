package com.cmbgold.system.utils

import com.cmbgold.system.bean.SystemComposableModel

object SystemItemUtils {

    fun getItemContent(composableModel: SystemComposableModel): String {
        var content = ""
        for (item in composableModel.children) {
            if (content?.isNullOrEmpty()) {
                content = item.name
            } else {
                content = "$content、${item.name}"

            }
        }
        return content
    }
}