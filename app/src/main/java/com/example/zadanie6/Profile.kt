package com.example.zadanie6

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {

    // это чтобы доступ был
    lateinit var imageProfile : ImageView
    lateinit var imageProfileOriginal : Bitmap
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imageProfile = findViewById(R.id.imageView3)
        dialog = Dialog(this)

        // сохраняем изначальную картинку
        var drawable = imageProfile.drawable as BitmapDrawable
        imageProfileOriginal = drawable.bitmap


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), 100
            )
        }
    }

    // открытие окна диалога по картинке профиля
    fun showDialog(view : View) {
        dialog.setContentView(R.layout.dialog_profile)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }

    // открытие камеры
    fun showCamera(view : View) {
        var intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100)
        dialog.hide()
    }

    // удаление фотографии (возвращаю первоначальную)
    fun deleteFotoProfile(view : View) {
        // imageProfile.setImageDrawable(null)
        imageProfile.setImageBitmap(imageProfileOriginal)
        dialog.hide()
    }

    // замена изображения в профиле
    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val captureImage = data?.extras?.get("data") as Bitmap
            imageProfile.setImageBitmap(captureImage)
        }
    }


    // открыть Категории
//    fun openActivityCategorii(view : View) {
//        var intent = Intent(applicationContext, CategoriiPomoshi().javaClass)
//        startActivity(intent)
//    }

}