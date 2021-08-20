package com.example.newsfeedtestapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.newsfeedtestapp.R
import com.example.newsfeedtestapp.databinding.ActivityWebViewBinding
import android.content.DialogInterface
import android.net.Uri

import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.example.newsfeedtestapp.utils.toastLong
import android.net.http.SslError
import android.webkit.*


class WebViewActivity : AppCompatActivity() {

    private var binding: ActivityWebViewBinding? = null
    private var webView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)

        val url = intent?.extras?.getString("url","")
        val title = intent?.extras?.getString("title","")

        webView = binding?.wvContent
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView?.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler,
                error: SslError
            ) {
                applicationContext.toastLong("PROCEEDING!? ${handler.toString()} ${error.url}")
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                applicationContext.toastLong("FINISHED!?")
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                applicationContext.toastLong("ERROR!!")
            }
        }

        webView?.loadUrl(url!!)

    }
}