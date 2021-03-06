package com.example.autentdenis

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.autentdenis.Sharedpref.SharedApp
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.io.IOException
import java.util.*

class ImgActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        var butonselect=findViewById<Button>(R.id.btn_choose_image)
        var butonsubir=findViewById<Button>(R.id.btn_upload_image)

        butonselect.setOnClickListener { launchGallery() }
        butonsubir.setOnClickListener { uploadImage() }
    }
    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                    // Handle failures
                    Toast.makeText(this, "error upload", Toast.LENGTH_LONG).show()
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String){
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        //val db = Firebase.firestore
        if(SharedApp.prefs.perfil=="1"){
            val db = Firebase.firestore
            var correu = SharedApp.prefs.email
            var nom=SharedApp.prefs.perfilname
            var edad=SharedApp.prefs.perfiledad
//            if(correu!=null)
//                db.collection("Users").document(SharedApp.prefs.email!!).get().addOnCompleteListener{
//                    nom = it.result?.get("nom") as String
//                    edad = it.result?.get("edad") as String
//                }
            val database = db.collection("Users").document(SharedApp.prefs.email!!)
            val img = hashMapOf(
                "nom" to nom,
                "img" to uri,
                "edad" to edad
            )
            database.set(img as Map<String, String>)
                .addOnSuccessListener {
                    Log.d(
                        "TAG",
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }

        }else {
            val database =
                db.collection("review").document(SharedApp.prefs.email!!).collection("menu")
                    .document(SharedApp.prefs.name.toString()!!)
            val img = hashMapOf(
                "img" to uri
            )
            database.update(img as Map<String, String>)
                .addOnSuccessListener {
                    Log.d(
                        "TAG",
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
        }

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            Toast.makeText(this, "Select", Toast.LENGTH_LONG).show()

            val img = findViewById<ImageView>(R.id.image_preview)
            Picasso.with(this).load(filePath).into(img)
        }
    }
}