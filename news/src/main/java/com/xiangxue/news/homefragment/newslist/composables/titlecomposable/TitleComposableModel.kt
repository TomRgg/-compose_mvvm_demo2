package com.xiangxue.news.homefragment.newslist.composables.titlecomposable

import com.xiangxue.base.compose.composablemodel.IBaseComposableModel

data class TitleComposableModel(
    var title: String,
    var channelId: String,
    var channelName: String,
    var link: String,
    var pubDate: String,
    var source: String
) : IBaseComposableModel()
