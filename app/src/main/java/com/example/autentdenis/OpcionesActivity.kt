package com.example.autentdenis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.databinding.ActivityOpcionesBinding

class OpcionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpcionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_opciones)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_opciones)
        binding.buttonPerfil.setOnClickListener {
            val intent= Intent(this, LoggedActivity::class.java)
            startActivity(intent)
        }
        binding.buttonplats.setOnClickListener {
            val intent= Intent(this, PlatoActivity::class.java)
            startActivity(intent)
        }
        binding.buttonreviews.setOnClickListener {
            val intent= Intent(this, VerReviewActivity::class.java)
            startActivity(intent)
        }
    }
}