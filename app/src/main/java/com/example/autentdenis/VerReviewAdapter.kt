package com.example.autentdenis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VerReviewAdapter(private val context: Context,
                       private val list: ArrayList<valoracion>,) : RecyclerView.Adapter<VerReviewAdapter.ViewHolder>()  {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.textViewreview2)
        val nom: TextView = view.findViewById(R.id.textViewreviw4)
        val valoracion: TextView = view.findViewById(R.id.textViewreview5)
        val image: ImageView = view.findViewById(R.id.imageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemreview,parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.count()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.email.text=data.email
        holder.nom.text=data.nom
        holder.valoracion.text=data.valoracion
        //image falta
    }
}