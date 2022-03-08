package com.xiangxue.news.homefragment.newslist

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xiangxue.base.compose.composablemanager.ComposableItem
import com.xiangxue.base.compose.lazycolumn.LoadMoreListHandler
import com.xiangxue.news.homefragment.api.Channel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val BUNDLE_KEY_PARAM_CHANNEL_ID = "bundle_key_param_channel_id"
const val BUNDLE_KEY_PARAM_CHANNEL_NAME = "bundle_key_param_channel_name"

@Composable
fun NewsListComposable(channel: Channel) {
    val context = LocalContext.current as Activity
    val bundle = Bundle()

    bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_ID, channel.channelId)
    bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_NAME, channel.name)
    val viewModel = ViewModelProvider(
        context as ViewModelStoreOwner, SavedStateViewModelFactory(
            context.application,
            context as SavedStateRegistryOwner, bundle
        )
    )[channel.channelId + channel.name, NewsListViewModel::class.java]

    val listState = rememberLazyListState()

    val rememberSwipeRefreshState = rememberSwipeRefreshState(
        false
    )
    SwipeRefresh(state = rememberSwipeRefreshState, onRefresh = {
        viewModel.tryToRefresh()
    }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 10.dp,
                top = 10.dp,
                start = 16.dp,
                end = 16.dp
            ),
            state = listState
        ) {
            items(viewModel.dataList) {
                ComposableItem(item = it)
            }
        }

        LoadMoreListHandler(listState = listState) {
            viewModel.tryToLoadNextPage()
        }
    }
}
