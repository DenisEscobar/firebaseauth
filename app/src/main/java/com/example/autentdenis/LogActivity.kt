package com.example.autentdenis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.databinding.ActivityLogBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_log)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log)

        auth = Firebase.auth
//        val a=0
        val buttonAuth = findViewById<Button>(R.id.buttonLog)
        binding.button.setOnClickListener {
        if(!(binding.editTextTextPersonEmail.text.toString().isEmpty() || binding.editTextTextPersonPassword.text.toString().isEmpty())) {
            signInWithEmailAndPassword(
                binding.editTextTextPersonEmail.text.toString(),
                binding.editTextTextPersonPassword.text.toString()
            )
        }
//            when (a) {
//                0 -> {
//                    signInWithEmailAndPassword("denis.escobar@institutvidreres.cat","123456")
//                }
//                1 -> {
//                    signInWithEmailAndPassword("denis.escobar1@institutvidreres.cat","654321")
//                }
//                2 -> {
//                    createAccount("denis.escobar1@institutvidreres.cat","654321")
//                }
//                3 -> {
//                    createAccount("denis.escobar@institutvidreres.cat","123456")
//                }
//                else -> {
//                    signOut()
//                }
//            }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }
    private fun signInWithEmailAndPassword(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("success", "signInWithEmail:success")
                    Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("fail", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null){
            val intent= Intent(this, OpcionesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun reload() {

    }
    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}