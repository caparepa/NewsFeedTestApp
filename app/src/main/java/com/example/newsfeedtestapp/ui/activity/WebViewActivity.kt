package com.example.newsfeedtestapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.newsfeedtestapp.R
import com.example.newsfeedtestapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private var binding: ActivityWebViewBinding? = null
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        val url = intent?.extras?.getString("url","")
        val title = intent?.extras?.getString("title","")

        webView = binding?.wvContent
        webView?.webViewClient = WebViewClient()

        webView?.loadUrl(url!!)

    }
}