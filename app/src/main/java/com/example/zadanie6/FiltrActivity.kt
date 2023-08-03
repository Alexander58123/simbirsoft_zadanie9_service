package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class FiltrActivity : AppCompatActivity() {
    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FilterAdapter

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtr)

        buttonhead = findViewById(R.id.menuCategorii2)
        nav = findViewById(R.id.BottomNavagation)

        recyclerView = findViewById(R.id.filtrKetegoriiPomoshi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FilterAdapter(fillList())
        recyclerView.adapter = adapter // передаем в адаптер наш список с данными

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@FiltrActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@FiltrActivity, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@FiltrActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }

        // слушатель для RecyclerView
        adapter.setOnClickListener(object :
            FilterAdapter.OnClickListener {
            override fun onClick(position: Int, model: FilterData) {

                // в зависимости от позиции переключаем Switch
                when (position) {
                    0 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(0)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    1 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(1)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    2 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(2)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    3 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(3)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                }
            }
        })
    }

    fun fillList(): List<FilterData> {
        val data = mutableListOf<FilterData>()
        data.add(FilterData("Деньгами", Switch(this)))
        data.add(FilterData("Вещами", Switch(this)))
        data.add(FilterData("Проф. помощью", Switch(this)))
        data.add(FilterData("Волонтерством", Switch(this)))
        return data
    }
}
