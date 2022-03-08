package com.cmbgold.system.compose.systemItem

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cmbgold.system.bean.SystemComposableModel
import com.cmbgold.system.bean.SystemItemComposeModel


class SystemListItemComposeProvider : PreviewParameterProvider<SystemItemComposeModel> {

    var bean = SystemItemComposeModel(
        "", 1, "HuangTao_Zoey", false, 60, "Android Studio相关",
        false, 13, "", "", "", true, "", 3029, "",
        "https://www.jianshu.com/p/f406d535a8bc", "2018-06-20 11:51", "未知时间",
        "", "", 1529466669000, 150, 0, null,
        "", 60, "开发环境", "Android trace文件抓取原理", 0,
        -1, 1, 0
    )

    override val values: Sequence<SystemItemComposeModel>
        get() = listOf(bean).asSequence()

}