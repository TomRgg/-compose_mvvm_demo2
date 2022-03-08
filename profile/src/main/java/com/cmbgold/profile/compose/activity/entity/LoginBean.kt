package com.cmbgold.profile.compose.activity.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginBean(
    @Json(name = "admin")
    val admin: Boolean,
    @Json(name = "coinCount")
    val coinCount: Int,
    @Json(name = "collectIds")
    val collectIds: List<Int>,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "publicName")
    val publicName: String,
    @Json(name = "token")
    val token: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "username")
    val username: String
)
