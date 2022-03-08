package com.cmbgold.system.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.xiangxue.base.compose.composablemodel.IBaseComposableModel


@JsonClass(generateAdapter = true)
data class SystemComposableModel(

    @Json(name = "children")
    val children: List<SystemComposableModel>,
    @Json(name = "courseId")
    val courseId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: String,
    @Json(name = "parentChapterId")
    val parentChapterId: String,
    @Json(name = "userControlSetTop")
    val userControlSetTop: Boolean,
    @Json(name = "visible")
    val visible: String

) : IBaseComposableModel()