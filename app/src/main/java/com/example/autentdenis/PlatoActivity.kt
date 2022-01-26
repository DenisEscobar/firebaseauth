package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlatoActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plato)


        val db = Firebase.firestore
        val docRef = db.collection("review")

        docRef
            .whereEqualTo("precio", "6")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    Log.d("document", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ver", "get failed with ", exception)
            }

    }
}