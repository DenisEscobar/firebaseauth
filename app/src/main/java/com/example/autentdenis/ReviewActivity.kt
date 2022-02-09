package com.example.autentdenis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.autentdenis.Sharedpref.SharedApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        buscar()
        val textnom = findViewById<TextView>(R.id.textViewnomplat)
        textnom.text = SharedApp.prefs.name.toString()
        val crear = findViewById<Button>(R.id.crearreview)
        val valoraciones = findViewById<EditText>(R.id.editTextNumber)
        crear.setOnClickListener {
            var nom = valoraciones.text.toString()
            val user = hashMapOf(
                "valoracion" to nom
            )
            crear(user)
        }
        val modificar = findViewById<Button>(R.id.modificarreview)
        modificar.setOnClickListener {
            var nom = valoraciones.text.toString()
            val user = hashMapOf(
                "valoracion" to nom
            )
            modificar(user)
        }
        val borrar = findViewById<Button>(R.id.borrarreview)
        borrar.setOnClickListener {
            borrar()
        }
        val foto = findViewById<Button>(R.id.buttonfoto)
        foto.setOnClickListener {
            SharedApp.prefs.perfil="0"
            val intent= Intent(this, ImgActivity::class.java)
            startActivity(intent)
        }
    }
    fun buscar() {
        val db = Firebase.firestore
        var correu = SharedApp.prefs.email
        val valoraciones = findViewById<EditText>(R.id.editTextNumber)
        if (correu != null) {
            val database =
                db.collection("review")
                    .document(SharedApp.prefs.email!!)
                    .collection("menu")
                    .document(SharedApp.prefs.name.toString()!!)
            database.get().addOnCompleteListener {
                valoraciones.setText(it.result?.get("valoracion") as String?)
            }
        }
    }
    fun crear(user: HashMap<String, String>){
        val db = Firebase.firestore
        val database = db.collection("review").document(SharedApp.prefs.email!!).collection("menu").document(SharedApp.prefs.name.toString()!!)

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
        val database = db.collection("review").document(SharedApp.prefs.email!!).collection("menu").document(SharedApp.prefs.name.toString()!!)

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
        db.collection("review").document(SharedApp.prefs.email!!).collection("menu").document(SharedApp.prefs.name.toString()!!)
            .delete()
            .addOnSuccessListener {}
    }
}