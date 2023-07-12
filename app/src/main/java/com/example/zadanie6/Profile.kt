package com.example.zadanie6

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {

    // это чтобы доступ был
    lateinit var imageProfile : ImageView
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

      //  val bottomNavigationView: BottomNavigationView = findViewById(R.id.BottomNavagation);
      //  bottomNavigationView.itemIconTintList = null

        imageProfile = findViewById(R.id.man_profile)
        dialog = Dialog(this)
    }

    fun showDialog(view : View) {
        dialog.setContentView(R.layout.dialog_profile)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }


}