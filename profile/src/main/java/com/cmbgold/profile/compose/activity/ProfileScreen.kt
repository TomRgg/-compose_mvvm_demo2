package com.cmbgold.profile.compose.activity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmbgold.profile.R
import com.cmbgold.profile.compose.activity.model.ProfileModel
import com.xiangxue.base.compose.navigation.NavigationItemController

@Preview
@Composable
fun ProfileScreen() {
    val icon by remember {
        mutableStateOf(ProfileModel?.info?.icon ?: "")
    }

    val nickname by remember {
        mutableStateOf(ProfileModel?.info?.nickname ?: "")
    }

    val id by remember {
        mutableStateOf(ProfileModel?.info?.id ?: 0)
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
    ) {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(10.dp)
        ) {
            if (id == 0) {
                Image(
                    ImageBitmap.imageResource(id = R.drawable.profile_user_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp)
                )
            } else if (icon?.isNullOrEmpty()) {
                Image(
                    ImageBitmap.imageResource(id = R.drawable.default_user_icron_new),
                    contentDescription = null,
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp)
                )
            } else {
                NetworkProfileImage(
                    url = icon,
                    R.drawable.default_user_icron_new,
                    modifier = Modifier
                        .requiredHeight(height = 72.dp)
                        .requiredWidth(72.dp)
                        .clip(RoundedCornerShape(36.dp))
                )
            }
        }

        if (id == 0) {
            Button(
                modifier = Modifier
                    .size(142.dp, 40.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    NavigationItemController.navigateOther("login") {
                        launchSingleTop = true
                        popUpTo("profile")
                    }
                },
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            ) {
                Text(
                    text = "登录",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = nickname,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(0.dp, 32.dp, 0.dp, 0.dp)
                .background(colorResource(id = R.color.cmb_white))
        ) {
            Row(
                Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.single_share_new),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "分享",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp, 0.dp),
                    color = colorResource(id = R.color.cmb_dark_grey),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
            Divider(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxWidth()
                    .height(1.dp),
                color = colorResource(id = R.color.color_A3A3A7)
            )

            Row(
                Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.brand_private),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "收藏",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp, 0.dp),
                    color = colorResource(id = R.color.cmb_dark_grey),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
            Divider(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxWidth()
                    .height(1.dp),
                color = colorResource(id = R.color.color_A3A3A7)
            )

            Row(
                Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth()
                    .clickable {
                        NavigationItemController.navigateOther("setting") {
//                            launchSingleTop = true
                            popUpTo("profile")
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "设置",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    color = colorResource(id = R.color.cmb_dark_grey),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
            Divider(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxWidth()
                    .height(1.dp),
                color = colorResource(id = R.color.color_A3A3A7)
            )
        }

    }
}