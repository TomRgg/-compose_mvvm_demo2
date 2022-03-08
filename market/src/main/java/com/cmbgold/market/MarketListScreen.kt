package com.cmbgold.market

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.cmbgold.market.compose.MarketItemCompose
import com.cmbgold.market.entity.MarketListItemComposableModel
import com.cmbgold.market.viewmodel.MarketListViewModel
import com.xiangxue.base.compose.composablemanager.ComposableItem
import com.xiangxue.base.compose.lazycolumn.LoadMoreListHandler
import com.xiangxue.base.compose.views.TitleComposeView

const val BUNDLE_KEY_PARAM_CHANNEL_ID = "bundle_key_param_channel_id"
const val BUNDLE_KEY_PARAM_CHANNEL_NAME = "bundle_key_param_channel_name"

@Composable
fun MarketListScreen() {

    val context = LocalContext.current as Activity
    val bundle = Bundle()
    val viewModel = ViewModelProvider(
        context as ViewModelStoreOwner, SavedStateViewModelFactory(
            context.application,
            context as SavedStateRegistryOwner, bundle
        )
    )["market_list_cache", MarketListViewModel::class.java]

    val listState = rememberLazyListState()

    Column {
        TitleComposeView(title = "广场", showBack = false) {

        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 0.dp,
                top = 0.dp,
                start = 0.dp,
                end = 0.dp
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
