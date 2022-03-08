package com.rgg.webviewlibs.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.rgg.webviewlibs.R
import com.rgg.webviewlibs.base.webviewclient.WebViewCallBack
import com.rgg.webviewlibs.databinding.FragmentWebViewBinding
import com.rgg.webviewlibs.entity.WebEntity

class WebViewFragment : Fragment(), WebViewCallBack {

    private lateinit var binding: FragmentWebViewBinding

    private var entity: WebEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entity = arguments?.run {
            getSerializable(WebViewActivity.ENTITY)?.let {
                it as WebEntity
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater, container, false)
        load()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setEvent()
    }

    @SuppressLint("LongLogTag")
    private fun load() {
        entity?.let { webEntity ->
            val baseWebView = BaseWebView(context)
            baseWebView.settings.javaScriptEnabled = true
            baseWebView.registerWebViewCallBack(this)
            val url = webEntity?.url ?: ""
            if (!url.isNullOrEmpty()) {
                baseWebView.loadUrl(url)
            } else {
                baseWebView.loadDataWithBaseURL(
                    null,
                    entity?.html ?: "",
                    "text/html; charset=utf-8",
                    "UTF-8",
                    null
                )
            }
//            binding.webView.loadUrl("file:///android_asset/demo.html")
            binding.refreshLayout.setColorSchemeResources(R.color.cmb_gold)
            binding.refreshLayout.setOnRefreshListener {
                baseWebView.reload()
                binding.refreshLayout.isRefreshing = false
            }
            binding.refreshLayout.isEnabled = webEntity?.isNativeRefresh ?: false
            binding.errorContainerLayout.addContentLayout(baseWebView)
            binding.errorContainerLayout.showLoading()
            binding.errorContainerLayout.setRefreshOnClickListener {
                baseWebView.reload()
                binding.errorContainerLayout.showContent()
                binding.errorContainerLayout.showLoading()
            }
        }
    }

    @SuppressLint("LongLogTag")
    private fun setEvent() {
        //解决refreshlayout与webview冲突
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWebView()?.run {
                this is BaseWebView
            }?.let {
                if (it) {
                    binding.errorContainerLayout.contentView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                        binding?.refreshLayout?.isEnabled = scrollY <= 0
                    }
                }
            }
        }

        if (activity is WebViewActivity) {
            (activity as WebViewActivity).setGoBackListener(object : GoBackListener {
                override fun back() {
                    goBack()
                }
            })
        }
    }

    override fun pageStarted(url: String?) {
        binding.errorContainerLayout.showLoading()
        binding.errorContainerLayout.showContent()
    }

    override fun pageFinished(url: String?) {
        binding.errorContainerLayout.showContent()
    }

    override fun onError() {
//        binding.errorContainerLayout.showLoadFailedError()
    }

    override fun updateTitle(title: String?) {
        if (entity?.userNativeTitle == true) {
            return
        } else {
            title?.run {
                activity?.let {
                    (it as WebViewActivity).updateToolbarTitle(this)
                }
            }
        }
    }

    override fun progress(progress: Int) {
        binding?.errorContainerLayout?.setProgress(progress)
    }

    private fun getWebView(): BaseWebView? {
        binding?.errorContainerLayout?.contentView?.run {
            return this as BaseWebView
        }
        return null
    }

    fun goBack() {
        getWebView()?.canGoBack()?.run {
            if (this) {
                getWebView()?.goBack()
            } else {
                this@WebViewFragment.activity?.finish()
            }
        }
    }

    companion object {

        const val TAG = "WebViewFragment"

        fun newInstance(entity: WebEntity, activity: FragmentActivity): WebViewFragment {
            val fragment = WebViewFragment()
            Bundle().apply {
                putSerializable(WebViewActivity.ENTITY, entity)
            }.apply {
                fragment.arguments = this
            }
            return fragment
        }
    }
}