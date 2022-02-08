package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autentdenis.Sharedpref.SharedApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VerReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_review)

        val db = Firebase.firestore
        val docRef = db.collection("review").document(SharedApp.prefs.email.toString()).collection("menu")
        var valoracion=valoracion("","","","")
        var valoraciones= arrayListOf<valoracion>()
        var i : Int = 0
        docRef
            //.whereEqualTo("precio", "6")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    valoraciones.add(
                        valoracion(
                            SharedApp.prefs.email.toString(),
                            document.id,
                            document.get("valoracion").toString()+"€",
                            document.get("img").toString()+""
                        )
                    )
                }
                val recyclerView: RecyclerView = findViewById(R.id.recylceviewverreview)
                recyclerView.layoutManager= LinearLayoutManager(this)
                recyclerView.adapter= VerReviewAdapter(this, valoraciones)
            }
            .addOnFailureListener { exception ->
                Log.d("ver", "get failed with ", exception)
            }



    }
}