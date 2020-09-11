package com.fabirt.roka.features.detail.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fabirt.roka.R
import kotlinx.android.synthetic.main.fragment_web_detail.*

class WebDetailFragment : Fragment(), WebViewDelegate {
    private val args: WebDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        setupWebView()
    }

    private fun setupWebView() {
        tvUrl.text = args.url
        tvTitle.text = args.title
        val client = RecipeWebViewClient().apply {
            delegate = this@WebDetailFragment
        }
        webView.webViewClient = client
        webView.loadUrl(args.url)
        webView.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK
                && event.action == KeyEvent.ACTION_UP
                && webView.canGoBack()
            ) {
                webView.goBack()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    override fun pageFinished(title: String, url: String) {
        tvUrl?.text = url
        tvTitle?.text = title
    }

}

class RecipeWebViewClient : WebViewClient() {
    var delegate: WebViewDelegate? = null

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if (view?.title != null && url != null) {
            delegate?.pageFinished(view.title, url)
        }
    }
}

interface WebViewDelegate {
    fun pageFinished(title: String, url: String)
}