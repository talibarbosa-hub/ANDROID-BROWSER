package com.example.lipowsbrowser

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {
    // Private
    private val WEBURL = "https://google.com"
    private val FONTE_SEARCH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        // Home IMG
        imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Back IMG
        imgBackf.setOnClickListener {
            if(webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(this, "Nao Existe Historico de Navegação", Toast.LENGTH_LONG).show()
            }
        }
        // Avancar IMG
        imgGo.setOnClickListener {
            if (webView.canGoForward()){
                  webView.goForward()
            } else {
                Toast.makeText(this, "Nao Existe Historico de Navegação", Toast.LENGTH_LONG).show()
            }
        }
        // Refresh IMG
        imgAtualizar.setOnClickListener {
             webView.reload()
        }
        // Refresh
        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }

        // Search
        SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                p0?.let {
                    if(URLUtil.isValidUrl(it)) {
                        // url
                        webView.loadUrl(it)
                    } else {
                        webView.loadUrl("$WEBURL$FONTE_SEARCH$it")
                    }
                }

                return false
            }

        })

        // WebView

        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                SearchView.setQuery(url, false)

                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                swipeRefresh.isRefreshing = false
            }

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl(WEBURL)

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }

    }
}
