package com.xiangxue.news.homefragment.newslist.composables.titlepicturecomposable

import com.xiangxue.base.compose.composablemodel.IBaseComposableModel

data class TitlePictureComposableModel(
    var title: String,
    var pictureUrl: String,
    var channelId: String,
    var channelName: String,
    var link: String,
    var pubDate: String,
    var source: String,
    var desc: String
) : IBaseComposableModel()
