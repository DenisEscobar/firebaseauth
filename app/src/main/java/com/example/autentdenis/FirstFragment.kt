package com.example.autentdenis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.databinding.FragmentFirstBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirstFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= DataBindingUtil.inflate<FragmentFirstBinding>(inflater,
            R.layout.fragment_first,container,false)

        auth = Firebase.auth
        val a=0
        val buttonAuth = binding.buttonAutho
        buttonAuth.setOnClickListener {
            when (a) {
                0 -> {
                    signInWithEmailAndPassword("denis.escobar@institutvidreres.cat","123456")
                }
                1 -> {
                    signInWithEmailAndPassword("denis.escobar1@institutvidreres.cat","654321")
                }
                2 -> {
                    createAccount("denis.escobar1@institutvidreres.cat","654321")
                }
                3 -> {
                    createAccount("denis.escobar@institutvidreres.cat","123456")
                }
                else -> {
                    signOut()
                }
            }
        }

        return binding.root
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
        this.activity?.let {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("success", "signInWithEmail:success")
                        Toast.makeText(this.activity, "Authentication success.", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("fail", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this.activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        this.activity?.let {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(this.activity, "Authentication success.", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this.activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null){

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
