package com.example.autentdenis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAuth = findViewById<Button>(R.id.buttonLog)
        buttonAuth.setOnClickListener {
            val intent= Intent(this, LogActivity::class.java)
            startActivity(intent)

        }
        val buttonregistre = findViewById<Button>(R.id.buttonregister)
        buttonregistre.setOnClickListener {
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}