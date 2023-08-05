package com.example.zadanie6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val spisokFiltra: List<FilterData>) :
    RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazvPunkta: TextView = itemView.findViewById(R.id.punktFilterRecyclerView)
        var switchFilter: Switch = itemView.findViewById(R.id.switch2)
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
        holder.nazvPunkta.text = spisokFiltra.get(position).nazvanie
        holder.switchFilter = spisokFiltra.get(position).switchFilter  // проверить view

        // Finally add an onclickListener to the item.
        val item = spisokFiltra[position]
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
                holder.switchFilter.isChecked = !holder.switchFilter.isChecked // обратный флаг
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // интерфейс
    interface OnClickListener {
        fun onClick(position: Int, model: FilterData)
    }
}
