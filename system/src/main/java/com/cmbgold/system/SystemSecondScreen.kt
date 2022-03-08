package com.cmbgold.system

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.cmbgold.system.viewmodel.SystemListViewModel
import com.cmbgold.system.viewmodel.SystemSecondListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xiangxue.base.compose.composablemanager.ComposableItem
import com.xiangxue.base.compose.lazycolumn.LoadMoreListHandler
import com.xiangxue.base.compose.views.TitleComposeView

const val BUNDLE_KEY_PARAM_ID = "id"

@Composable
fun SystemSecondScreen(id: String?, name: String?) {
    val context = LocalContext.current as Activity
    val bundle = Bundle()
    bundle.putString(BUNDLE_KEY_PARAM_ID, id)
    val viewModel = ViewModelProvider(
        context as ViewModelStoreOwner, SavedStateViewModelFactory(
            context.application,
            context as SavedStateRegistryOwner, bundle
        )
    )[id + name, SystemSecondListViewModel::class.java]

    val listState = rememberLazyListState()

    val rememberSwipeRefreshState = rememberSwipeRefreshState(
        true
    )

    val enable = remember {
        mutableStateOf<Boolean>(true)
    }
    SwipeRefresh(state = rememberSwipeRefreshState, onRefresh = {
        enable.value = false
        viewModel.tryToRefresh()
    }, swipeEnabled = enable.value) {
        if (rememberSwipeRefreshState.isRefreshing) {
            Handler(Looper.myLooper()!!).postDelayed({
                rememberSwipeRefreshState.isRefreshing = false
                enable.value = true
            }, 400)
        }
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 60.dp,
                    top = 0.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
                state = listState
            ) {
                items(viewModel.dataList) {
                    ComposableItem(item = it)
//                MarketItemCompose(composableModel = it as MarketListItemComposableModel)
                }
            }
            LoadMoreListHandler(listState = listState) {
                viewModel.tryToLoadNextPage()
            }
        }
    }
}