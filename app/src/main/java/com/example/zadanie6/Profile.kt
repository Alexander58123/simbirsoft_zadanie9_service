package com.example.zadanie6

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class Profile : AppCompatActivity() {

    // это чтобы доступ был
    lateinit var imageProfile: ImageView
    lateinit var imageProfileOriginal: Bitmap
    lateinit var dialog: Dialog
    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        buttonhead = findViewById(R.id.menu_pomoch)
        imageProfile = findViewById(R.id.imageView3)
        dialog = Dialog(this)

        // сохраняем изначальную картинку
        val drawable = imageProfile.drawable as BitmapDrawable
        imageProfileOriginal = drawable.bitmap

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                100,
            )
        }

        // выбранный пункт
        nav = findViewById(R.id.BottomNavagation)
        nav.menu.getItem(4).setChecked(true)

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@Profile, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    val intent = Intent(this@Profile, SearchActivity::class.java)
                    startActivity(intent)
                }
                R.id.news -> {
                    val intent = Intent(this@Profile, NewsActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    // открытие окна диалога по картинке профиля
    fun showDialog(view: View) {
        dialog.setContentView(R.layout.dialog_profile)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }

    // открытие камеры
    fun showCamera(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 100)
        dialog.hide()
    }

    // удаление фотографии (возвращаю первоначальную)
    fun deleteFotoProfile(view: View) {
        // imageProfile.setImageDrawable(null)
        imageProfile.setImageBitmap(imageProfileOriginal)
        dialog.hide()
    }

    // открытие галлереи для выбора фотографии
    fun openGalleryPhoto(view: View) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 1)
        dialog.hide()
    }

    // замена изображения в профиле
    // обработка полученного результата

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // для камеры
        if (requestCode == 100) {
            val captureImage = data?.extras?.get("data") as Bitmap
            imageProfile.setImageBitmap(captureImage)
        }

        // для галереи
        if (requestCode == 1) {
            imageProfile.setImageURI(data?.data)
        }
    }
}
