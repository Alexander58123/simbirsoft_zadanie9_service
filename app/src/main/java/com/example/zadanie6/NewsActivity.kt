package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class NewsActivity : AppCompatActivity() {

    lateinit var nav: BottomNavigationView
    lateinit var novost1: FrameLayout
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var buttonFilter: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        buttonhead = findViewById(R.id.menuCategorii)
        nav = findViewById(R.id.BottomNavagation)
        buttonFilter = findViewById(R.id.filterButton)
        novost1 = findViewById(R.id.dataNovost1)

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@NewsActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        // открытие меню Фильтр
        buttonFilter.setOnClickListener {
            val intent = Intent(this@NewsActivity, FiltrActivity::class.java)
            startActivity(intent)
        }

        // открытие Новость1
        novost1.setOnClickListener {
            val intent = Intent(this@NewsActivity, SobutiePodrobno::class.java)
            startActivity(intent)
        }

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@NewsActivity, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@NewsActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }
    }
}
