package com.xiangxue.news.homefragment.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel

@JsonClass(generateAdapter = true)
data class ProjectItemBean(
    @Json(name = "curPage")
    var curPage: String,
    @Json(name = "datas")
    var datas: List<ProjectItemListBean>,
    @Json(name = "offset")
    var offset: String,
    @Json(name = "over")
    var over: Boolean,
    @Json(name = "pageCount")
    var pageCount: String,
    @Json(name = "size")
    var size: String,
    @Json(name = "total")
    var total: String,
)

@JsonClass(generateAdapter = true)
data class ProjectItemListBean(
    @Json(name = "apkLink")
    var apkLink: String? = null,
    @Json(name = "audit")
    var audit: String? = null,
    @Json(name = "author")
    var author: String? = null,
    @Json(name = "canEdit")
    var canEdit: Boolean,
    @Json(name = "chapterId")
    var chapterId: String? = null,
    @Json(name = "chapterName")
    var chapterName: String? = null,
    @Json(name = "collect")
    var collect: Boolean,
    @Json(name = "courseId")
    var courseId: String? = null,
    @Json(name = "desc")
    var desc: String? = null,
    @Json(name = "descMd")
    var descMd: String? = null,
    @Json(name = "envelopePic")
    var envelopePic: String? = null,
    @Json(name = "fresh")
    var fresh: Boolean,
    @Json(name = "host")
    var host: String? = null,
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "link")
    var link: String,
    @Json(name = "niceDate")
    var niceDate: String? = null,
    @Json(name = "niceShareDate")
    var niceShareDate: String? = null,
    @Json(name = "origin")
    var origin: String? = null,
    @Json(name = "prefix")
    var prefix: String? = null,
    @Json(name = "projectLink")
    var projectLink: String? = null,
    @Json(name = "publishTime")
    var publishTime: String? = null,
    @Json(name = "realSuperChapterId")
    var realSuperChapterId: String? = null,
    @Json(name = "selfVisible")
    var selfVisible: String? = null,
    @Json(name = "shareDate")
    var shareDate: String? = null,
    @Json(name = "shareUser")
    var shareUser: String? = null,
    @Json(name = "superChapterId")
    var superChapterId: String? = null,
    @Json(name = "superChapterName")
    var superChapterName: String? = null,
    @Json(name = "tags")
    var tags: List<TagBean>,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "visible")
    var visible: String? = null,
    @Json(name = "zan")
    var zan: String? = null
) : IBaseComposableModel()

@JsonClass(generateAdapter = true)
data class TagBean(
    @Json(name = "name")
    var name: String,
    @Json(name = "url")
    var url: String
)
