package com.xiangxue.arch_demo

import com.cmbgold.system.SYSTEM_SECOND_KEY

sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String,
    var messageCount: Int = 0
) {
    object Home : NavigationItem("home", R.drawable.ic_home, "项目")
    object Market : NavigationItem("market", R.drawable.ic_movie, "广场")
    object Books : NavigationItem("books", R.drawable.ic_book, "体系")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "我的", messageCount = 0)
    object Login : NavigationItem("login", 0, "")
    object Setting : NavigationItem("setting", 0, "")
    object SystemSecondList : NavigationItem(SYSTEM_SECOND_KEY, 0, "")
}