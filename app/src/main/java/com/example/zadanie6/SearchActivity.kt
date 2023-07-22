package com.example.zadanie6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zadanie6.databinding.ActivitySearchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {

    // лист фрагментов для ViewPager2
    private val fragList = listOf(
        SearchFragmentOne.newInstance(),
        SearchFragmentTwo.newInstance()
    )

    private val fragListTitles = listOf(
        "По мероприятиям",
        "По НКО"
    )

    lateinit var nav : BottomNavigationView
    lateinit var buttonhead : ExtendedFloatingActionButton
    private lateinit var binding : ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // viewPager синхронизирован с табами
        val adapter = VpAdapter(this, fragList)
        binding.ViewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.ViewPager) {
            tab, position -> tab.text = fragListTitles[position]
        }.attach()


        buttonhead = findViewById(R.id.menuNews)
        nav = findViewById(R.id.BottomNavagation)

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@SearchActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@SearchActivity, Profile::class.java)
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