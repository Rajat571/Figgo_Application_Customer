package com.pearlorganisation.figgo.UI
//Neeraj
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.pearlorganisation.figgo.R


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class GoogleWebview : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_webview)

        val mywebview = findViewById<View>(R.id.webView) as WebView
        // mywebview.loadUrl("https://www.javatpoint.com/");

        /*String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8"); */

        // mywebview.loadUrl("https://www.javatpoint.com/");

        /*String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8"); */mywebview.loadUrl("https://play.google.com/store/apps")
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.setSupportZoom(true)
    }

}