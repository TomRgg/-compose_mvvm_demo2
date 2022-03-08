package com.cmbgold.system

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cmbgold.system.bean.SystemComposableModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.xiangxue.base.compose.navigation.NavigationItemController
import com.xiangxue.base.compose.views.TitleComposeView
import kotlinx.coroutines.launch

const val SYSTEM_SECOND_KEY = "SystemSecondList"

@ExperimentalPagerApi
@Composable
fun SystemSecondLineScreen() {
    //获取传递过来的datas数据
    val source = NavigationItemController.dataMap[SYSTEM_SECOND_KEY] as SystemComposableModel
    val data = source?.children
    var tabIndex by remember { mutableStateOf(0) }
    var initialOffscreenLimit = if (data.size / 2 >= 1) {
        data.size
    } else {
        1
    }
    val pagerState =
        rememberPagerState(
            initialPage = 0,
            initialOffscreenLimit = initialOffscreenLimit,
            pageCount = data.size
        )

    if (data?.isEmpty()) return
    val coroutineScope = rememberCoroutineScope()
    Column {
        TitleComposeView(title = source.name, showBack = true) {
            NavigationItemController.popBackStack()
        }
        ScrollableTabRow(
            backgroundColor = colorResource(R.color.colorPrimary),
            contentColor = Color.White,
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            data.forEachIndexed { index, text ->
                val selected = tabIndex == index
                val interactionSource = remember {
                    MutableInteractionSource()
                }
                val isPress = interactionSource.collectIsPressedAsState().value
                Tab(selected = selected,
                    text = {
                        Text(
                            text = text.name,
                            color = if (isPress || pagerState.currentPage == index) Color.White else Color.White.copy(
                                0.4f
                            )
                        )
                    },
                    onClick = {
                        tabIndex = index
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    })
            }
        }
        HorizontalPager(
            state = pagerState
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SystemSecondScreen(id = data[index].id ?: "0", name = data[index].name)
            }
        }
    }
}