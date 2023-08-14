package com.example.zadanie6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(val spisokNews: List<NewsData>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    var count = 0

    // вытаскиваем все элементы
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageItem)
        val title: TextView = itemView.findViewById(R.id.titleItem)
        val description: TextView = itemView.findViewById(R.id.descriptionItem)
        val data: Button = itemView.findViewById(R.id.buttonData)
    }

    // возвращаем наш ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_news_item, parent, false)
        return MyViewHolder(itemView)
    }

    // размер списка в RecyclerView
    override fun getItemCount(): Int {
        return spisokNews.size
    }

    // связываем данные адаптера и передаваемого списка
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("NewsAdapter", "OnBindViewHolder, count ${++count}")
        holder.image.setImageResource(spisokNews.get(position).imageId)  // картинка
        holder.title.text = spisokNews.get(position).title               // заголовок
        holder.description.text = spisokNews.get(position).description   // описание
        holder.data.text = spisokNews.get(position).data                 // дата события

    }

}