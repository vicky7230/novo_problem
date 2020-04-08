package com.vicky7230.newsapp.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.vicky7230.newsapp.R
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewUI : AppCompatActivity() {

    companion object {
        val URL = "url"

        fun getStartIntent(context: Context, url: String, sourceName: String): Intent {
            val intent = Intent(context, WebViewUI::class.java)
            intent.putExtra(URL, url)
            intent.putExtra(NewsFragment.SOURCE_NAME, sourceName)
            return intent
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null
            && intent.getStringExtra(URL) != null
            && intent.getStringExtra(NewsFragment.SOURCE_NAME) != null
        ) {
            supportActionBar?.title = intent.getStringExtra(NewsFragment.SOURCE_NAME)

            val webSettings: WebSettings = web_view.settings
            webSettings.javaScriptEnabled = true
            web_view.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    web_progress.visibility = View.GONE
                    web_view.visibility = View.VISIBLE
                }
            }
            web_view.loadUrl(intent.getStringExtra(URL))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }
}
