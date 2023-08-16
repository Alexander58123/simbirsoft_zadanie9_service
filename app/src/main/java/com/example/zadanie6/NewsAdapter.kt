package com.example.zadanie6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val spisokNews = mutableListOf<NewsData>()
    private var onClickListener: NewsAdapter.OnClickListener? = null
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
        holder.image.setImageResource(spisokNews.get(position).imageId) // картинка
        holder.title.text = spisokNews.get(position).title // заголовок
        holder.description.text = spisokNews.get(position).description // описание
        holder.data.text = spisokNews.get(position).data // дата события


        //  Finally add an onclickListener to the item.
        val item = spisokNews[position]
        holder.data.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }

    // сеттер для передачи списка (старый без DiffUtils)
//    fun setData(nashSpisok: List<NewsData>) {
//        spisokNews.clear()
//        spisokNews.addAll(nashSpisok)
//    }

    // с использование DiffUtils
    fun setData(nashSpisok: List<NewsData>) {
        spisokNews.clear()
        val callback = NewsDiffCallback(spisokNews, nashSpisok)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        spisokNews.addAll(nashSpisok)
    }


    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // интерфейс
    interface OnClickListener {
        fun onClick(position: Int, model: NewsData)
    }
}
