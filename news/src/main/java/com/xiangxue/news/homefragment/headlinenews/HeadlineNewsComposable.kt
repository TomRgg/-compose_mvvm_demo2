package com.xiangxue.news.homefragment.headlinenews

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import kotlinx.coroutines.launch
import com.xiangxue.news.homefragment.newslist.NewsListComposable

@ExperimentalPagerApi
@Composable
fun HeadlineNewsComposable() {
    val viewModel: HeadlineNewsViewModel = viewModel()
    var tabIndex by remember { mutableStateOf(0) }
    val pagerState =
        rememberPagerState(
            initialPage = 0,
            initialOffscreenLimit = 1,
            pageCount = viewModel.dataList.size
        )

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
                            color = if (isPress || pagerState.currentPage == index) Color.White else Color.White.copy(0.4f)
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
                NewsListComposable(viewModel.dataList[index])
            }
        }
    }
}