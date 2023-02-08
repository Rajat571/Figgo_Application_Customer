package com.pearlorganisation.figgo.UI

import android.os.Bundle
import android.webkit.WebView
import com.pearlorganisation.figgo.pearlLib.BaseClass
import com.pearlorganisation.figgo.R

class CurrentAboutActivity : BaseClass() {
    private lateinit var webView: WebView

    override fun setLayoutXml() {

    }

    override fun initializeViews() {

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
        setContentView(R.layout.activity_current_about)
        webView = findViewById<WebView>(R.id.webabout)



        webView.loadUrl("https://figgocabs.com/about/")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
}