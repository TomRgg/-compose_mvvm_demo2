package com.xiangxue.news.homefragment.newslist.composables.titlecomposable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider


class TitleComposeProvider : PreviewParameterProvider<TitleComposableModel> {

    var title = TitleComposableModel(
        "hhahahahaha",
        "怕频道",
        "频道名字",
        "sssssss",
        "2022.10.1",
        "环球网"
    )

    override val values: Sequence<TitleComposableModel>
        get() = listOf(title).asSequence()

}