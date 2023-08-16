package com.example.zadanie6

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SobutiePodrobno : AppCompatActivity() {

    lateinit var titleSobutie: TextView
    lateinit var kartinka: ImageView
    lateinit var desription: TextView
    lateinit var data: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobutie_podrobno)

        var tekushiiObject = intent.getParcelableExtra<NewsData>("sobutieData")

        titleSobutie = findViewById(R.id.TitleSobutie)
        kartinka = findViewById(R.id.imageSobutie)
        desription = findViewById(R.id.descriptionSobutie)
        data = findViewById(R.id.timeSobutie)

        // меняем данные из переданного объекта
        titleSobutie.text = tekushiiObject?.title
        if (tekushiiObject != null) {
            kartinka.setImageResource(tekushiiObject.imageId)
        }
        desription.text = tekushiiObject?.description
        data.text = tekushiiObject?.data
    }
}
