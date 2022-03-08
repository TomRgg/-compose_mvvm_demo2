package com.cmbgold.system.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cmbgold.system.bean.SystemComposableModel


class SystemItemComposeProvider : PreviewParameterProvider<SystemComposableModel> {

    var title = SystemComposableModel(
        emptyList(),
        "13",
        "60",
        "Android Studio相关",
        "1000",
        "150",
        false,
        "1"
    )

    override val values: Sequence<SystemComposableModel>
        get() = listOf(title).asSequence()

}