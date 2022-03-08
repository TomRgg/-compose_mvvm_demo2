package com.xiangxue.news.homefragment.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectTabBean(
    @Json(name = "children")
    var children: List<Any>,
    @Json(name = "courseId")
    var courseId: String,
    @Json(name = "id")
    var id: String,
    @Json(name = "name")
    var name: String,
    @Json(name = "order")
    var order: String,
    @Json(name = "parentChapterId")
    var parentChapterId: String,
    @Json(name = "userControlSetTop")
    var userControlSetTop: Boolean,
    @Json(name = "visible")
    var visible: String
)
