package com.xiangxue.news.homefragment.newslist.composables.titlepicturecomposable

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.auto.service.AutoService
import com.rgg.webviewlibs.base.WebViewActivity
import com.rgg.webviewlibs.entity.WebEntity
import com.xiangxue.base.R
import com.xiangxue.base.compose.composablemanager.IComposableService
import com.xiangxue.news.homefragment.newslist.commoncomposables.NetworkImage

@AutoService(IComposableService::class)
class TitlePictureComposableService : IComposableService<TitlePictureComposableModel> {
    override val content: @Composable (item: TitlePictureComposableModel) -> Unit = { item ->
        TitlePictureComposable(composableModel = item)
    }
    override val type: String
        get() = TitlePictureComposableModel::class.java.name
}

@Preview
@Composable
fun TitlePictureComposable(
    @PreviewParameter(TitlePictureComposeProvider::class)
    composableModel: TitlePictureComposableModel
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                Modifier
                    .weight(1f)
                    .height(180.dp)
            ) {
                Text(
                    text = composableModel.title,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 0.dp, end = 0.dp, top = 5.dp, bottom = 0.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
//                Row(
//                    Modifier
//                        .wrapContentWidth()
//                        .padding(16.dp, 0.dp, 16.dp, 5.dp)
//                ) {
                Text(
                    text = composableModel.desc,
                    color = colorResource(id = com.xiangxue.base.R.color.cmb_medium_grey),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1f)
                        .padding(0.dp, 5.dp, 0.dp, 5.dp),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = composableModel.source,
                        color = colorResource(id = com.xiangxue.base.R.color.cmb_medium_grey),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .wrapContentWidth()
                    )
//                }
                    Text(
                        text = composableModel.pubDate,
                        color = colorResource(id = com.xiangxue.base.R.color.cmb_medium_grey),
                        fontSize = 12.sp,
                        modifier = Modifier
                    )
                }
            }
            Box(Modifier.padding(5.dp, 5.dp, 0.dp, 5.dp)) {
//                Image(
//                    ImageBitmap.imageResource(id = R.mipmap.ic_action_picture_light),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .width(120.dp)
//                        .height(100.dp)
//                )
                NetworkImage(
                    url = composableModel.pictureUrl,
                    modifier = Modifier
                        .requiredHeight(height = 180.dp)
                        .requiredWidth(120.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
    val pxValue = with(LocalDensity.current) { 1.dp.toPx() }

    Spacer(
        Modifier
            .background(Color.Gray)
            .padding(16.dp, 0.dp)
            .fillMaxWidth()
            .height((1 / pxValue).dp)
    )
}