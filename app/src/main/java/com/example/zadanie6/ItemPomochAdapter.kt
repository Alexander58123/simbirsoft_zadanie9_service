package com.example.zadanie6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ItemPomochAdapter(private val itemPomochList : ArrayList<ItemPomoch>) :
    RecyclerView.Adapter<ItemPomochAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pomoch,
            parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemPomochList[position]
        holder.imageId.setImageResource(currentItem.imageId)
        holder.titleId.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return itemPomochList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val imageId : ImageView = itemView.findViewById(R.id.image_punkta)
        val titleId : TextView = itemView.findViewById(R.id.title_punkta)
    }


}