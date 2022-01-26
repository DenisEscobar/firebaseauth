package com.example.autentdenis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        auth = Firebase.auth
        binding.buttonRegistrarse.setOnClickListener {
            if (!(binding.editTextEmail.text.toString()
                    .isEmpty() || binding.editTextName.text.toString()
                    .isEmpty() || binding.editTextPassword.text.toString().isEmpty())
            ) {
                if (binding.editTextPassword.text.toString().toInt() > 100000) {
                    createAccount(binding.editTextEmail.text.toString(),binding.editTextPassword.text.toString())

                }
            }
        }
    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("create", "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("create", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null){
            val intent= Intent(this, LogActivity::class.java)
            startActivity(intent)
        }
    }
}