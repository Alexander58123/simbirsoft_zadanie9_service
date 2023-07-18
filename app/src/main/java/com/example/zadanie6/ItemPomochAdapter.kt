package com.example.zadanie6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie6.databinding.ItemPomochBinding

class ItemPomochAdapter : RecyclerView.Adapter<ItemPomochAdapter.ItemPomochHolder>() {
    val itemPomochList = ArrayList<ItemPomoch>()

    class ItemPomochHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = ItemPomochBinding.bind(item)

        fun bind(itemPomoch: ItemPomoch) = with(binding) {
            imageList.setImageResource(itemPomoch.imageId)
            titleList.text= itemPomoch.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPomochHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pomoch, parent, false)
        return ItemPomochHolder(view)
    }

    override fun onBindViewHolder(holder: ItemPomochHolder, position: Int) {
        holder.bind(itemPomochList[position])
    }

    override fun getItemCount(): Int {
        return itemPomochList.size
    }

    fun addItemPomochElem(itemPomoch: ItemPomoch) {
        itemPomochList.add(itemPomoch)
        notifyDataSetChanged()
    }


}