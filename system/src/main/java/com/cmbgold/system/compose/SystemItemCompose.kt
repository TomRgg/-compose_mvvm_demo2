package com.cmbgold.system.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmbgold.system.SYSTEM_SECOND_KEY
import com.cmbgold.system.bean.SystemComposableModel
import com.cmbgold.system.utils.SystemItemUtils
import com.google.auto.service.AutoService
import com.xiangxue.base.R
import com.xiangxue.base.compose.composablemanager.IComposableService
import com.xiangxue.base.compose.navigation.NavigationItemController

@AutoService(IComposableService::class)
class SystemListService : IComposableService<SystemComposableModel> {
    override val content: @Composable (item: SystemComposableModel) -> Unit = {
        SystemItemCompose(it)
    }

    override val type: String
        get() = SystemComposableModel::class.java.name
}

@Preview
@Composable
fun SystemItemCompose(
    @PreviewParameter(SystemItemComposeProvider::class)
    composableModel: SystemComposableModel
) {
    val content = remember {
        mutableStateOf(SystemItemUtils.getItemContent(composableModel))
    }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = colorResource(id = R.color.cmb_white))
            .clickable {
                NavigationItemController.dataMap[SYSTEM_SECOND_KEY] = composableModel
                NavigationItemController.navigateOther(SYSTEM_SECOND_KEY) {
//                    popUpTo("profile")
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp, 0.dp, 8.dp),
                    text = composableModel?.name ?: "",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.color_2B2B2B)
                )

                Text(
                    text = content.value,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.color_838383)
                )
            }
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .background(
                    color = colorResource(
                        id = R.color.divider_color
                    )

                )
        )
    }
}