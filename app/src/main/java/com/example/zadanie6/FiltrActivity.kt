package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class FiltrActivity : AppCompatActivity() {
    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtr)

        buttonhead = findViewById(R.id.menuCategorii2)
        nav = findViewById(R.id.BottomNavagation)

        recyclerView = findViewById(R.id.filtrKetegoriiPomoshi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilterAdapter(fillList()) // передаем в адаптер наш список с данными

        // выводим наш  в фильтре
//        nashSpisok()

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
    }

    fun fillList(): List<String> {
        val data = mutableListOf<String>(
            "Деньгами",
            "Вещами",
            "Проф. помощью",
            "Волонтерством",
        )
        return data
    }

//    fun nashSpisok() {
//        // выводим наш список (мероприятия, фрагмент1)
//        var listView = findViewById<ListView>(R.id.filtrKetegoriiPomoshi)
//        val masiveKategoriy = arrayOf(
//            "Деньгами",
//            "Вещами", "Проф. помощью", "Волонтерством"
//        )
//        val arrayAdapter: ArrayAdapter<String>
//        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_selectable_list_item, masiveKategoriy)
//        listView?.adapter = arrayAdapter
//    }
}
