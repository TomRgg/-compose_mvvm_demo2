package com.cmbgold.profile.compose.activity

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.cmbgold.profile.compose.activity.viewmodel.LoginViewModel
import com.xiangxue.base.R
import com.xiangxue.base.compose.navigation.NavigationItemController

@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        LoginPage()
    }

}

@Preview
@Composable
fun LoginPage() {
    val context = LocalContext.current
    val bundle = Bundle()
    val viewModel =
        ViewModelProvider(
            context as ViewModelStoreOwner, SavedStateViewModelFactory(
                context.applicationContext as Application?,
                context as SavedStateRegistryOwner, bundle
            )
        )["login", LoginViewModel::class.java]

    var isLoading by remember {
        mutableStateOf(false)
    }

    var id by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cmb_white))
    ) {
        IconButton(onClick = {
            NavigationItemController.popBackStack()
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 66.dp)
        ) {
            LoginInput { name, password ->
                isLoading = true

                viewModel.login(name, password, { loginBean ->
//                    Toast.makeText(
//                        context.applicationContext,
//                        loginBean.toString(),
//                        Toast.LENGTH_SHORT
//                    )
//                        .show()
                    isLoading = false
                    NavigationItemController.popBackStack()
                }, { errMsg ->
                    Toast.makeText(
                        context.applicationContext,
                        errMsg, Toast.LENGTH_SHORT
                    ).show()
                    isLoading = false
                })
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(56.dp)
                            .padding(10.dp),
                        color = colorResource(id = R.color.colorPrimary)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginInput(
    request: (name: String, password: String) -> Unit
) {

    //玩android账号
    var name by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp),
            value = name,
            label = {
                Text(text = "账号")
            },
            placeholder = {
                Text(text = "请输入账号")
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = com.cmbgold.profile.R.drawable.ico_colse),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                        .clickable { name = "" })
            },//文本后图片
            onValueChange = { it ->
                name = it
            })

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
            value = password,
            label = {
                Text(text = "密码")
            },
            placeholder = {
                Text(text = "请输入密码")
            },
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                Image(
                    painter = painterResource(id = com.cmbgold.profile.R.drawable.ico_colse),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                        .clickable { password = "" })
            },//文本后图片
            onValueChange = { it ->
                password = it
            })

        Button(
            modifier = Modifier
                .size(142.dp, 40.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(16.dp, 0.dp),
            onClick = {
                request(name, password)
            },
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.White),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
        ) {
            Text(
                text = "登录",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}

