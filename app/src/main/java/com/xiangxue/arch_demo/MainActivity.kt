package com.xiangxue.arch_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.cmbgold.market.MarketListScreen
import com.cmbgold.profile.compose.activity.LoginScreen
import com.cmbgold.profile.compose.activity.ProfileScreen
import com.cmbgold.profile.compose.activity.SettingScreen
import com.cmbgold.system.SystemListScreen
import com.cmbgold.system.SystemSecondLineScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.xiangxue.base.compose.composablemanager.ComposableServiceManager
import com.xiangxue.base.compose.navigation.NavigationItemController
import com.xiangxue.base.compose.views.TitleComposeView
import com.xiangxue.news.homefragment.headlinenews.HeadlineNewsComposable2

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        window?.statusBarColor = resources.getColor(R.color.colorPrimary)
        ComposableServiceManager.collectServices()
        setContent {
            val isShowBottom = mutableStateOf(true)
            NavigationItemController.isShowBottomNavigation = isShowBottom
            MainScreen()
        }
    }

    override fun onBackPressed() {
        if (NavigationItemController.isShowBottomNavigation?.value == true) {
            super.onBackPressed()
        } else {
            NavigationItemController.popBackStack()
        }
    }
}

@Preview
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
//        topBar = { TopBar() },
        bottomBar = {
            if (NavigationItemController.isShowBottomNavigation?.value == true) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        Navigation(navController = navController)
    }
}

@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
//            HeadlineNewsComposable()
            HeadlineNewsComposable2()
        }
        composable(NavigationItem.Market.route) {
            MarketListScreen()
        }
        composable(NavigationItem.Books.route) {
            SystemListScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
        composable(NavigationItem.Login.route) {
            LoginScreen()
        }
        composable(NavigationItem.Setting.route) {
            SettingScreen()
        }

        composable(NavigationItem.SystemSecondList.route) {
            SystemSecondLineScreen()
        }
    }
    NavigationItemController.setPageController(navController)
}

@Preview
@Composable
fun TopBar() {
    TitleComposeView(
        title = stringResource(id = R.string.menu_home),
        showBack = false,
        onBack = null
    )
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Market,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (item.messageCount != 0) {
                        BadgeBox(
                            backgroundColor = Color.White,
                            contentColor = Color.Red,
                            badgeContent = { Text("8") }) {
                            Icon(
                                ImageVector.Companion.vectorResource(id = item.icon),
                                contentDescription = item.title
                            )
                        }
                    } else {
                        Icon(painterResource(id = item.icon), contentDescription = item.title)
                    }
                },
                //icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}