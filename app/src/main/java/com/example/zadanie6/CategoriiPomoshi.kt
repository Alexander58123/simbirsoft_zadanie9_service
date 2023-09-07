package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CategoriiPomoshi : AppCompatActivity() {

    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorii_pomoshi)

        // наши объекты для работы
        nav = findViewById(R.id.BottomNavagation)
        buttonhead = findViewById(R.id.extendedFloatingActionButton)
        progressBar = findViewById(R.id.progressBarPomosh)

        if (savedInstanceState != null) {
            progressBar.visibility = View.GONE
        } else {
            val service: ExecutorService = Executors.newSingleThreadExecutor()
            service.execute(
                Runnable {
                    runOnUiThread(
                        Runnable {
                            progressBar.visibility = View.VISIBLE
                        },
                    )

                    Thread.sleep(5000)

                    runOnUiThread(
                        Runnable {
                            // вживляем фрагмент в активити (раньше через отдельный метод вызывался)
                            // replaceFragment(BlankFragment())
                            val fragmentManager = supportFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.place_holder, BlankFragment())
                            fragmentTransaction.commitAllowingStateLoss()
                            progressBar.visibility = View.GONE
                        },
                    )
                },
            )
        }

        // выбранный пункт
        nav.menu.getItem(2).setChecked(true)

        // меню bottom
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@CategoriiPomoshi, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@CategoriiPomoshi, SearchActivity::class.java)
                    startActivity(intent)
                }
                R.id.news -> {
                    val intent = Intent(this@CategoriiPomoshi, NewsActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun onResume() {
        super.onResume()
        // выбранный пункт
        nav.menu.getItem(2).setChecked(true)
    }

    // фрагмент функция
//    private fun replaceFragment(blankFragment: BlankFragment) {
//    }
}
