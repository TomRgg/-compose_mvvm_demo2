package com.cmbgold.market.compose

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmbgold.market.entity.MarketListItemComposableModel
import com.google.auto.service.AutoService
import com.rgg.webviewlibs.base.WebViewActivity
import com.rgg.webviewlibs.entity.WebEntity
import com.xiangxue.base.R
import com.xiangxue.base.compose.composablemanager.IComposableService
import com.xiangxue.base.compose.views.TitleComposeView

@AutoService(IComposableService::class)
class MarketListItemService : IComposableService<MarketListItemComposableModel> {

    override val content: @Composable (item: MarketListItemComposableModel) -> Unit =
        {
            MarketItemCompose(it)
        }

    override val type: String
        get() = MarketListItemComposableModel::class.java.name
}


@Preview
@Composable
fun MarketItemCompose(
    @PreviewParameter(MarketItemComposeProvider::class)
    composableModel: MarketListItemComposableModel
) {

    val refresh = remember {
        mutableStateOf(composableModel.fresh ?: false)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.cmb_white))
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

        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 10.dp)
        )
        {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (refresh.value) {
                    Surface(
                        shape = RoundedCornerShape(2.dp),
                        border = BorderStroke(
                            1.dp,
                            colorResource(id = R.color.colorAccent)
                        ),
                        modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
                    ) {
                        Text(
                            text = "新",
                            color = colorResource(id = R.color.colorAccent),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }

                Text(
                    text = composableModel?.shareUser ?: "",
                    color = colorResource(id = R.color.inactive_text_color),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = composableModel?.niceShareDate ?: "",
                    color = colorResource(id = R.color.inactive_text_color),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(0.dp, 0.dp)
                )
            }

            Text(
                text = composableModel?.title ?: "",
                color = colorResource(id = R.color.color_2B2B2B),
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .padding(0.dp, 16.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${
                        composableModel?.superChapterName
                    }/${
                        composableModel?.chapterName
                    }",
                    modifier = Modifier.weight(1f),
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.inactive_text_color),
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .background(
                    color = colorResource(
                        id = R.color.divider_color
                    )
                )
        )
    }
}