package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.databinding.ActivityLoggedBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class LoggedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_logged)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_logged)

        binding.creardocumento.setOnClickListener {
            if(!(binding.editTextTextPersonDocument.text.toString().isEmpty())) {
                if (!(binding.editTextTextPersonName.text.toString()
                        .isEmpty() && binding.editTextTextPersonName2.text.toString().isEmpty())
                ) {
                    val user = hashMapOf("name" to "",)
                    if (!(binding.editTextTextPersonName.text.toString()
                            .isEmpty() || binding.editTextTextPersonName2.text.toString().isEmpty())
                    ) {
                        val user = hashMapOf(
                            "name" to binding.editTextTextPersonName.text.toString(),
                            "edad" to binding.editTextTextPersonName2.text.toString(),
                        )
                        crear(user)
                    } else if (!(binding.editTextTextPersonName.text.toString().isEmpty())) {
                        val user = hashMapOf(
                            "name" to binding.editTextTextPersonName.text.toString(),
                        )
                        crear(user)
                    } else if (!(binding.editTextTextPersonName2.text.toString().isEmpty())) {
                        val user = hashMapOf(
                            "edad" to binding.editTextTextPersonName2.text.toString(),
                        )
                        crear(user)
                    }

                }
            }
        }
    }
    fun crear(user: HashMap<String,String>){
        val db = Firebase.firestore
        val database = db.collection("Users").document(binding.editTextTextPersonDocument.text.toString())

        if(database==null) {
            database.set(user)
                .addOnSuccessListener {
                    Log.d(
                        "TAG",
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
        }else{
            database.update(user as Map<String, String>)
                .addOnSuccessListener {
                Log.d(
                    "TAG",
                    "DocumentSnapshot successfully written!"
                )
            }
                .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }

        }
    }
}