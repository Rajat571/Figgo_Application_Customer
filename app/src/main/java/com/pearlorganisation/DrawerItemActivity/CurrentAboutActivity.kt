package com.pearlorganisation.DrawerItemActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.pearlorganisation.figgo.R

class CurrentAboutActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_about)
        webView = findViewById<WebView>(R.id.webabout)

        webView.loadUrl("https://figgocabs.com/about/")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
}