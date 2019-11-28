package com.example.lipowsbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgLipows.setOnClickListener {
            val intent = Intent(this, NavActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        Toast.makeText(this, "CB 1.0", Toast.LENGTH_LONG).show()
    }
}
