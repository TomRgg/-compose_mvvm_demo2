package com.xiangxue.news.homefragment.headlinenews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.xiangxue.base.compose.composablemanager.ComposableServiceManager

class HeadlineNewsFragment : Fragment() {
    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ComposableServiceManager.collectServices()
        return ComposeView(requireContext()).apply {
            setContent {
//                HeadlineNewsComposable()
                HeadlineNewsComposable2()
            }
        }
    }
}