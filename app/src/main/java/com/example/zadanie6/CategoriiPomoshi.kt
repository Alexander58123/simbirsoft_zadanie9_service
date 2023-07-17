package com.example.zadanie6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.jetbrains.annotations.NotNull

class CategoriiPomoshi : AppCompatActivity() {

    lateinit var nav : BottomNavigationView
    lateinit var buttonhead : ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorii_pomoshi)

        // наши объекты для работы
        nav = findViewById(R.id.BottomNavagation)
        buttonhead = findViewById(R.id.extendedFloatingActionButton)

        // выбранный пункт
        nav.menu.getItem(2).setChecked(true)

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@CategoriiPomoshi, Profile::class.java)
                    startActivity(intent)
                }

            }

            true
        }

//        buttonhead.setOnClickListener {
//            val intent = Intent(this@CategoriiPomoshi, CategoriiPomoshi::class.java)
//            startActivity(intent)
//        }



    }

    override fun onResume() {
        super.onResume()
        // выбранный пункт
        nav.menu.getItem(2).setChecked(true)
    }

}