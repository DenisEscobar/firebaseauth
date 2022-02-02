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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_plato
        )
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

                    platos.add(plato(document.id,(document.get("precio").toString()+"€")))
                    Log.d("document1234", "1-${platos.size}")
                }
                for (i in 0..2) {
                    Log.d("document1234", "${platos.get(i)}")
                }

                val recyclerView: RecyclerView = binding.recycleview
                recyclerView.layoutManager= LinearLayoutManager(this)
                recyclerView.adapter= PlatoAdapter(this, platos)

            }
            .addOnFailureListener { exception ->
                Log.d("ver", "get failed with ", exception)
            }




    }
}