package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autentdenis.databinding.ActivityPlatoBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlatoActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityPlatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_plato)

        val db = Firebase.firestore
        val docRef = db.collection("menu")
        var plato=plato("","")
        var platos= arrayListOf<plato>()
        var i : Int = 0
        docRef
            //.whereEqualTo("precio", "6")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){

                    Log.d("document1234", "${document.id} => ${document.data} => ${documents.size()}")

                    platos.add(plato(document.id,"5"))
                    //platos.
                    //platos!![0].nom=document.id
//                    platos!![0].preu=document.data.toString()
                    Log.d("document1234", "1-${platos.get(1)}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ver", "get failed with ", exception)
            }

//for (i in 1..3) {
//    platos?.get(i)?.let { Log.d("document1234", it.nom) }
//}
//        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
//        recyclerView.layoutManager= LinearLayoutManager(this)
//        recyclerView.adapter= PlatoAdapter(application, platos!!)

    }
}