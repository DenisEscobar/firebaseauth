package com.example.autentdenis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.Sharedpref.SharedApp
import com.example.autentdenis.databinding.ActivityLoggedBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.*

class LoggedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_logged)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_logged)
        buscar()

        binding.button2.setOnClickListener {
            SharedApp.prefs.perfil="1"
            val intent= Intent(this, ImgActivity::class.java)
            startActivity(intent)
        }
        binding.creardocumento.setOnClickListener {
            var nom = binding.editTextTextPersonName.text.toString()
            var edad = binding.editTextTextPersonName2.text.toString()
            val user = hashMapOf(
                "nom" to nom,
                "edad" to edad,
            )
            crear(user)
            buscar()
//            if(!(binding.editTextTextPersonDocument.text.toString().isEmpty())) {
//                if (!(binding.editTextTextPersonName.text.toString()
//                        .isEmpty() && binding.editTextTextPersonName2.text.toString().isEmpty())
//                ) {
//                    val user = hashMapOf("name" to "",)
//                    if (!(binding.editTextTextPersonName.text.toString()
//                            .isEmpty() || binding.editTextTextPersonName2.text.toString().isEmpty())
//                    ) {
//                        val user = hashMapOf(
//                            "name" to binding.editTextTextPersonName.text.toString(),
//                            "edad" to binding.editTextTextPersonName2.text.toString(),
//                        )
//                        crear(user)
//                    } else if (!(binding.editTextTextPersonName.text.toString().isEmpty())) {
//                        val user = hashMapOf(
//                            "name" to binding.editTextTextPersonName.text.toString(),
//                        )
//                        crear(user)
//                    } else if (!(binding.editTextTextPersonName2.text.toString().isEmpty())) {
//                        val user = hashMapOf(
//                            "edad" to binding.editTextTextPersonName2.text.toString(),
//                        )
//                        crear(user)
//                    }
//
//                }
//            }
        }
        binding.modificardocumento.setOnClickListener {
            var nom = binding.editTextTextPersonName.text.toString()
            var edad = binding.editTextTextPersonName2.text.toString()
            val user = hashMapOf(
                "nom" to nom,
                "edad" to edad,
            )
            modificar(user)
            buscar()
        }
        binding.borrardocumento.setOnClickListener {
            borrar()
            buscar()
        }

    }
    fun buscar(){
        val db = Firebase.firestore
        var correu = SharedApp.prefs.email
        binding.textViewemailpersonal.text = SharedApp.prefs.email
        if(correu!=null)
        db.collection("Users").document(correu).get().addOnCompleteListener{
            binding.editTextTextPersonName.setText(it.result?.get("nom") as String?)
            binding.editTextTextPersonName2.setText(it.result?.get("edad") as String?)
            Picasso.with(this).load(it.result?.get("img") as String?).into(binding.imageView2)
        }
    }
    fun crear(user: HashMap<String,String>){
        val db = Firebase.firestore
        val database = db.collection("Users").document(binding.textViewemailpersonal.text.toString())

        database.set(user)
            .addOnSuccessListener {
                Log.d(
                    "TAG",
                    "DocumentSnapshot successfully written!"
                )
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
    }
    fun modificar(user: HashMap<String,String>){
        val db = Firebase.firestore
        val database = db.collection("Users").document(binding.textViewemailpersonal.text.toString())

        database.update(user as Map<String, String>)
            .addOnSuccessListener {
                Log.d(
                    "TAG",
                    "DocumentSnapshot successfully written!"
                )
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
    }
    fun borrar(){
        val db = Firebase.firestore
        db.collection("Users").document(binding.textViewemailpersonal.text.toString())
            .delete()
            .addOnSuccessListener {}
    }
    override fun onResume() {
        // After a pause OR at startup
        buscar()
        super.onResume()
        //Refresh your stuff here
    }
}