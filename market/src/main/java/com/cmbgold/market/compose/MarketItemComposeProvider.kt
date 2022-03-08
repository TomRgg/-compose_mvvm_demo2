package com.cmbgold.market.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cmbgold.market.entity.MarketListItemComposableModel


class MarketItemComposeProvider : PreviewParameterProvider<MarketListItemComposableModel> {

    var title = MarketListItemComposableModel(
        "",
        1,
        "",
        false,
        494,
        "广场",
        false,
        13,
        "",
        "",
        "",
        true,
        "",
        21344,
        "https://juejin.cn/post/7057680571157184549",
        "9小时前",
        "9小时前",
        "",
        "",
        "",
        1643688139000,
        493,
        0,
        1643688139000,
        "moxiaov587",
        494,
        "广场Tab",
        "探索 Flutter 模拟事件触发",
        0,
        122577,
        0,
        0
    )

    override val values: Sequence<MarketListItemComposableModel>
        get() = listOf(title).asSequence()

}