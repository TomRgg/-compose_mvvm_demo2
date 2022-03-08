package com.rgg.webviewlibs.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.rgg.webviewlibs.R
import com.rgg.webviewlibs.common.WebServiceLoader
import com.rgg.webviewlibs.common.WebViewService
import com.rgg.webviewlibs.databinding.ActivityWebViewBinding
import com.rgg.webviewlibs.entity.WebEntity

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val ENTITY = "entity"
    }

    private lateinit var binding: ActivityWebViewBinding

    private var backListener: GoBackListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor = ActivityCompat.getColor(this, R.color.colorPrimary)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var entity = intent?.run {
            extras?.get(ENTITY) as WebEntity
        }
        entity?.isShowActionBar?.run {
            if (!this) return
            binding.flActionBar.visibility = View.VISIBLE
            binding.ivBack.setOnClickListener {
                onBackPressed()
            }
            binding.tvTitle.text = entity?.title ?: ""

            //load fragment in container
            WebServiceLoader.load(WebViewService::class.java)
                ?.getWebViewFragment(entity, this@WebViewActivity)?.run {
                    this@WebViewActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, this, WebViewFragment.TAG).commit()
                }
        }
    }

    fun updateToolbarTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setGoBackListener(listener: GoBackListener) {
        this.backListener = listener
    }

    override fun onBackPressed() {
        if (this.backListener != null) {
            this.backListener?.back()
        } else {
            super.onBackPressed()
        }
    }

}