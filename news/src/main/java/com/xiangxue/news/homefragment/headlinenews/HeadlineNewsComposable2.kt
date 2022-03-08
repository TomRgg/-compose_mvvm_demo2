package com.xiangxue.news.homefragment.headlinenews

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.xiangxue.news.R
import com.xiangxue.news.homefragment.NewsListComposable2
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun HeadlineNewsComposable2() {
    val viewModel: HeadlineNewsViewModel2 = viewModel()
    var tabIndex by remember { mutableStateOf(0) }
    var initialOffscreenLimit = if (viewModel.dataList.size / 2 >= 1) {
        viewModel.dataList.size / 2
    } else {
        1
    }
    val pagerState =
        rememberPagerState(
            initialPage = 0,
            initialOffscreenLimit = initialOffscreenLimit,
            pageCount = viewModel.dataList.size
        )

    if (viewModel.dataList.size == 0) return
    val coroutineScope = rememberCoroutineScope()
    Column {
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
            viewModel.dataList.forEachIndexed { index, text ->
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
                NewsListComposable2(viewModel.dataList[index])
            }
        }
    }
}