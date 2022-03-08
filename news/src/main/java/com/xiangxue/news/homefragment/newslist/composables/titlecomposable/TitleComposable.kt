package com.xiangxue.news.homefragment.newslist.composables.titlecomposable

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.auto.service.AutoService
import com.rgg.webviewlibs.base.WebViewActivity
import com.rgg.webviewlibs.entity.WebEntity
import com.xiangxue.base.R
import com.xiangxue.base.compose.composablemanager.IComposableService
import org.checkerframework.framework.qual.Bottom

@AutoService(IComposableService::class)
class TitleComposableService : IComposableService<TitleComposableModel> {
    override val content: @Composable (item: TitleComposableModel) -> Unit = { item ->
        TitleComposable(composableModel = item)
    }
    override val type: String
        get() = TitleComposableModel::class.java.name
}

@Preview
@Composable
fun TitleComposable(
    @PreviewParameter(TitleComposeProvider::class)
    composableModel: TitleComposableModel
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(0.dp, 5.dp, 0.dp, 5.dp)
            .clickable {
                val webEntity = WebEntity()
                webEntity.isNativeRefresh = true
                webEntity.isShowActionBar = true
                webEntity.title = composableModel?.title ?: ""
                webEntity.url = composableModel?.link ?: ""
                val bundle = Bundle()
                bundle.putSerializable(WebViewActivity.ENTITY, webEntity)
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = composableModel.title,
                color = colorResource(id = R.color.cmb_dark_grey),
                fontSize = 16.sp,
            )
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = composableModel.source,
                    color = colorResource(id = R.color.cmb_medium_grey),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                )
            }
            Text(
                text = composableModel.pubDate,
                color = colorResource(id = R.color.cmb_medium_grey),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(0.dp, 2.dp, 0.dp, 0.dp)
            )
        }

    }

    val pxValue = with(LocalDensity.current) { 1.dp.toPx() }

    Spacer(
        Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
            .height((1 / pxValue).dp)
    )
}