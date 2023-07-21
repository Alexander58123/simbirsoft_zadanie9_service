package com.example.zadanie6

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class Novosti : AppCompatActivity() {

    lateinit var nav : BottomNavigationView
    lateinit var buttonhead : ExtendedFloatingActionButton


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novosti)


        buttonhead = findViewById(R.id.menuNews)
        nav = findViewById(R.id.BottomNavagation)

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@Novosti, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@Novosti, Profile::class.java)
                    startActivity(intent)
                }
            }

            true
        }


    }


    override fun onResume() {
        super.onResume()
        // выбранный пункт
        nav.menu.getItem(0).setChecked(true)
    }
}