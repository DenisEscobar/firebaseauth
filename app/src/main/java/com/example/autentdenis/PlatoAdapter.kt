package com.example.autentdenis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlatoAdapter(private val context: Context,
                   private val list: List<plato>,) : RecyclerView.Adapter<PlatoAdapter.ViewHolder>()  {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val first: TextView = view.findViewById(R.id.textfirst)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item,parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.count()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.first.text=data.nom
    }
}