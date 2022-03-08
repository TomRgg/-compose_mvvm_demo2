package com.cmbgold.system

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
import com.cmbgold.system.model.SystemListModel
import com.cmbgold.system.viewmodel.SystemListViewModel
import com.xiangxue.base.compose.composablemanager.ComposableItem
import com.xiangxue.base.compose.lazycolumn.LoadMoreListHandler
import com.xiangxue.base.compose.views.TitleComposeView


@Composable
fun SystemListScreen() {

    val context = LocalContext.current as Activity
    val bundle = Bundle()
    val viewModel = ViewModelProvider(
        context as ViewModelStoreOwner, SavedStateViewModelFactory(
            context.application,
            context as SavedStateRegistryOwner, bundle
        )
    )["system_list", SystemListViewModel::class.java]

    val listState = rememberLazyListState()

    Column {
        TitleComposeView(title = "体系", showBack = false) {

        }
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
//        LoadMoreListHandler(listState = listState) {
//            viewModel.tryToLoadNextPage()
//        }
    }
}
