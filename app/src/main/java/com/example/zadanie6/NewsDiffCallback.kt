package com.example.zadanie6

// DiffUtils для RecyclerView

import androidx.recyclerview.widget.DiffUtil

class NewsDiffCallback(
    private val oldList: List<NewsData>,
    private val newList: List<NewsData>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    // если id старого и нового элемента совпадают, то true, иначе false
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    // проверяем содержимое объектов (старого и нового листа)
    // у data класса equls уже переопределенный, чтобы объекты сравнивать
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}
