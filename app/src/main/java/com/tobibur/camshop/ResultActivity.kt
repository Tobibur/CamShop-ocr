package com.tobibur.camshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import android.content.Intent
import android.net.Uri


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val string : String = intent.getStringExtra("Text")
        editText.setText(string)

        fab.setOnClickListener {
            val url : String = "https://www.flipkart.com/search?q=$string"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}
