package com.xiangxue.news.homefragment.newslist.composables.titlepicturecomposable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider


class TitlePictureComposeProvider : PreviewParameterProvider<TitlePictureComposableModel> {

    var title = TitlePictureComposableModel(
        "hhahahahaha",
        "http://image1.chinanews.com.cn/cnsupload/big/2016/12-23/4-426/46d72050964b467bb965674c0ec3b0d3.jpg",
        "怕频道",
        "频道名字",
        "sssssss",
        "2022.10.1",
        "环球网",
        ""
    )

    override val values: Sequence<TitlePictureComposableModel>
        get() = listOf(title).asSequence()

}