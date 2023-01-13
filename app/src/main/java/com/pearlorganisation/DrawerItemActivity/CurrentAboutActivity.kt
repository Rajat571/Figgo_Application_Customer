package com.pearlorganisation.DrawerItemActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R

class CurrentAboutActivity : BaseClass() {
    private lateinit var webView: WebView

    override fun setLayoutXml() {
        setContentView(R.layout.activity_current_about)
    }

    override fun initializeViews() {
        webView = findViewById<WebView>(R.id.webabout)
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        webView.loadUrl("https://figgocabs.com/about/")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
}