package com.example.zadanie6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(var spisokFiltra: List<FilterData>) :
    RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazvPunkta: TextView = itemView.findViewById(R.id.punktFilterRecyclerView)
        val switchFilter: Switch = itemView.findViewById(R.id.switch2)
    }

    // сюда макет отдельного элемента
    // и возвращаем наш ViewHolder
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
        holder.switchFilter.isChecked = spisokFiltra.get(position).switchFilter.isChecked // забираем статус из нашего списка
        spisokFiltra[position].switchFilter = holder.switchFilter


        //  Finally add an onclickListener to the item.
        val item = spisokFiltra[position]
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
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
