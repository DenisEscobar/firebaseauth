package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.autentdenis.Sharedpref.SharedApp

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val textnom = findViewById<TextView>(R.id.textViewnomplat)
        textnom.text = SharedApp.prefs.name.toString()
    }
}