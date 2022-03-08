package com.cmbgold.profile.compose.activity

import android.app.Application
import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.cmbgold.profile.R
import com.cmbgold.profile.compose.activity.model.ProfileModel
import com.cmbgold.profile.compose.activity.viewmodel.LoginViewModel
import com.cmbgold.profile.compose.activity.viewmodel.LogoutViewModel
import com.xiangxue.base.compose.navigation.NavigationItemController
import com.xiangxue.base.compose.views.TitleComposeView
import com.xiangxue.base.utils.ToastUtil

@Preview
@Composable
fun SettingScreen() {

    val openDialog = remember { mutableStateOf(false) }

    val isLogin = remember {
        mutableStateOf(ProfileModel.info?.id ?: 0 != 0)
    }

    val context = LocalContext.current

    val viewModel =
        ViewModelProvider(
            context as ViewModelStoreOwner, SavedStateViewModelFactory(
                context.applicationContext as Application?,
                context as SavedStateRegistryOwner, Bundle()
            )
        )["logout", LogoutViewModel::class.java]

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxSize()
//                .padding(0.dp, 32.dp, 0.dp, 0.dp)
                .background(colorResource(id = R.color.cmb_white))
        ) {

            TitleComposeView(title = "设置", showBack = true) {
                NavigationItemController.popBackStack()
            }

            Row(
                Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "昵称",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.color_2B2B2B),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
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
                Text(
                    text = "清除缓存",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.color_2B2B2B),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
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
                Text(
                    text = "关于",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.color_2B2B2B),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
            }
            Divider(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                    .fillMaxWidth()
                    .height(1.dp),
                color = colorResource(id = R.color.color_A3A3A7)
            )

            if (isLogin.value) {
                Button(
                    modifier = Modifier
                        .size(142.dp, 40.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    onClick = {
//                LogoutDialog()
                        openDialog.value = true
                    },
                    shape = RoundedCornerShape(50),
//            border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
                ) {
                    Text(
                        text = "退出登录",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "提示")
                },
                text = {
                    Text(
                        "确认退出登录？"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.logout({
                                ProfileModel.info = null
                                isLogin.value = false
                                openDialog.value = false

                            }, {
//                                ToastUtil.showToast(context, it)
                                ProfileModel.info = null
                                isLogin.value = false
                                openDialog.value = false

                            })
                        }
                    ) {
                        Text("确认", color = colorResource(id = R.color.colorPrimary))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("取消", color = colorResource(id = R.color.color_373737))
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun LogoutDialog() {

}
