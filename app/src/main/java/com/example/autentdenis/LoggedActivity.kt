package com.example.autentdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.autentdenis.Sharedpref.SharedApp
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
        buscar()
        binding.creardocumento.setOnClickListener {
            var nom = binding.editTextTextPersonName.text.toString()
            var edad = binding.editTextTextPersonName2.text.toString()
            val user = hashMapOf(
                "nom" to nom,
                "edad" to edad,
            )
            crear(user)
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
        }
        binding.borrardocumento.setOnClickListener { borrar() }

    }
    fun buscar(){
        val db = Firebase.firestore
        var correu = SharedApp.prefs.email
        binding.textViewemailpersonal.text = SharedApp.prefs.email
        if(correu!=null)
        db.collection("Users").document(correu).get().addOnCompleteListener{
            binding.editTextTextPersonName.setText(it.result?.get("nom") as String?)
            binding.editTextTextPersonName2.setText(it.result?.get("edad") as String?)
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
}