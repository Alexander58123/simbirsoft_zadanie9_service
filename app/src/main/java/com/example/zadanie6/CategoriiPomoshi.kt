package com.example.zadanie6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CategoriiPomoshi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorii_pomoshi)
    }


//    var view = bottomNavigationView.findViewById(R.id.menu_action_item);
//    view.performClick();

     // открыть профиль
    fun openActivityProfile(view : View) {
        var intent = Intent(applicationContext, Profile().javaClass)
        startActivity(intent)
    }
}