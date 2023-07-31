package com.example.zadanie6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val spisokFiltra: List<String>) :
    RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazvPunkta: TextView = itemView.findViewById(R.id.punktFilterRecyclerView)
    }

    // сюда макет отдельного элемента и возвращаем наш ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.filtr_atem_recyclerview, parent, false)
        return MyViewHolder(itemView)
    }

    // возвращает количество элементов
    override fun getItemCount(): Int {
        return spisokFiltra.size
    }

    // связываем текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nazvPunkta.text = spisokFiltra[position]
    }
}
