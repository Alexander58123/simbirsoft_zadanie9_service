package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class SobutiePodrobno : AppCompatActivity() {

    lateinit var titleBar: TextView
    lateinit var titleSobutie: TextView
    lateinit var kartinka: ImageView
    lateinit var desription: TextView
    lateinit var data: TextView

    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var arrowBack: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobutie_podrobno)

        nav = findViewById(R.id.BottomNavagation)
        buttonhead = findViewById(R.id.menuCategorii3)
        arrowBack = findViewById(R.id.arrovBackID)

        val tekushiiObject = intent.getParcelableExtra<NewsData>("sobutieData")

        titleBar = findViewById(R.id.titleBar)
        titleSobutie = findViewById(R.id.TitleSobutie)
        kartinka = findViewById(R.id.imageSobutie)
        desription = findViewById(R.id.descriptionSobutie)
        data = findViewById(R.id.timeSobutie)

        // меняем данные из переданного объекта
        titleBar.text = tekushiiObject?.title
        titleSobutie.text = tekushiiObject?.title
        if (tekushiiObject != null) {
            kartinka.setImageResource(tekushiiObject.imageId)
        }
        desription.text = tekushiiObject?.description
        data.text = tekushiiObject?.data

        // -------------------------  навигация -----------------
        // менюшка BootomMenu
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@SobutiePodrobno, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@SobutiePodrobno, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@SobutiePodrobno, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        // кнопка назад
        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}
