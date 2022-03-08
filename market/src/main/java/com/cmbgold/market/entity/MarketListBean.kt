package com.cmbgold.market.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel

@JsonClass(generateAdapter = true)
data class MarketListBean(

    @Json(name = "curPage")
    var curPage: Int,
    @Json(name = "datas")
    var data: List<MarketListItemComposableModel>,
    @Json(name = "offset")
    var offset: Int,
    @Json(name = "over")
    var over: Boolean,
    @Json(name = "pageCount")
    var pageCount: Int,
    @Json(name = "size")
    var size: Int,
    @Json(name = "total")
    var total: Int
): IBaseComposableModel()


@JsonClass(generateAdapter = true)
data class MarketListItemComposableModel(
    @Json(name = "apkLink")
    var apkLink: String,
    @Json(name = "audit")
    var audit: Int,
    @Json(name = "author")
    var author: String,
    @Json(name = "canEdit")
    var canEdit: Boolean,
    @Json(name = "chapterId")
    var chapterId: Int,
    @Json(name = "chapterName")
    var chapterName: String,
    @Json(name = "collect")
    var collect: Boolean,
    @Json(name = "courseId")
    var courseId: Int,
    @Json(name = "desc")
    var desc: String,
    @Json(name = "descMd")
    var descMd: String,
    @Json(name = "envelopePic")
    var envelopePic: String,
    @Json(name = "fresh")
    var fresh: Boolean,
    @Json(name = "host")
    var host: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "link")
    var link: String,
    @Json(name = "niceDate")
    var niceDate: String,
    @Json(name = "niceShareDate")
    var niceShareDate: String,
    @Json(name = "origin")
    var origin: String,
    @Json(name = "prefix")
    var prefix: String,
    @Json(name = "projectLink")
    var projectLink: String,
    @Json(name = "publishTime")
    var publishTime: Long,
    @Json(name = "realSuperChapterId")
    var realSuperChapterId: Int,
    @Json(name = "selfVisible")
    var selfVisible: Int,
    @Json(name = "shareDate")
    var shareDate: Long,
    @Json(name = "shareUser")
    var shareUser: String,
    @Json(name = "superChapterId")
    var superChapterId: Int,
    @Json(name = "superChapterName")
    var superChapterName: String,
    @Json(name = "title")
    var title: String,
    @Json(name = "type")
    var type: Int,
    @Json(name = "userId")
    var userId: Int,
    @Json(name = "visible")
    var visible: Int,
    @Json(name = "zan")
    var zan: Int
) : IBaseComposableModel()