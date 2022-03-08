package com.xiangxue.base.compose.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

object NavigationItemController {

    private var controller: NavHostController? = null

    var isShowBottomNavigation: MutableState<Boolean>? = null

    var dataMap = HashMap<String, Any>()

    fun setPageController(navHostController: NavHostController) {
        this.controller = navHostController
    }

    fun navigateOther(router: String, builder: NavOptionsBuilder.() -> Unit) {
        this.controller?.navigate(router, builder)
        isShowBottomNavigation?.value = false
    }

    fun popBackStack() {
        isShowBottomNavigation?.value = !(this.controller?.currentBackStackEntry?.id == "home" ||
                this.controller?.currentBackStackEntry?.id == "movies" ||
                this.controller?.currentBackStackEntry?.id == "books" ||
                this.controller?.currentBackStackEntry?.id == "profile")
        this.controller?.popBackStack()
    }
}