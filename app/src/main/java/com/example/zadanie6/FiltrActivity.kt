package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.json.JSONArray
import java.io.InputStream
import java.lang.Exception

class FiltrActivity : AppCompatActivity() {

    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var arrowBack: ImageView
    lateinit var filterCheckButton: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FilterAdapter

    lateinit var spisokForAdapter: List<FilterData>
    lateinit var spisokKategorii: List<Kategorii>

    lateinit var switch: Switch
    lateinit var switch1: Switch
    lateinit var switch2: Switch
    lateinit var switch3: Switch
    lateinit var pref1: SharedPreferences
    lateinit var pref2: SharedPreferences
    lateinit var pref3: SharedPreferences
    lateinit var pref4: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtr)

        buttonhead = findViewById(R.id.menuCategorii2)
        nav = findViewById(R.id.BottomNavagation)
        arrowBack = findViewById(R.id.arrovBackID)
        filterCheckButton = findViewById(R.id.filterButton)

        // наш список категорий, который спарсили из categorii.json
        spisokKategorii = read_json()

        recyclerView = findViewById(R.id.filtrKetegoriiPomoshi)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // инициализация Switch и SharedPreferences
        initSwitchandSharedPreferences()

        spisokForAdapter = fillList(switch, switch1, switch2, switch3)

        adapter = FilterAdapter(spisokForAdapter)
        recyclerView.adapter = adapter // передаем в адаптер наш список с данными

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@FiltrActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        // кнопка назад
        arrowBack.setOnClickListener {
            val intent = Intent(this@FiltrActivity, NewsActivity::class.java)
            startActivity(intent)
        }

        // кнопка подтверждения
        filterCheckButton.setOnClickListener {
            val intent = Intent(this@FiltrActivity, NewsActivity::class.java)
            startActivity(intent)
        }

        // навигация в BottomMenu
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@FiltrActivity, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@FiltrActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }

        // слушатель для Адаптера
        adapter.setOnClickListener(object :
            FilterAdapter.OnClickListener {
            override fun onClick(position: Int, model: FilterData) {
                // в зависимости от позиции переключаем Switch
                when (position) {
                    0 -> {
                        val switch = model.switchFilter
                        switch.isChecked = switch.isChecked != true
                    }
                    1 -> {
                        val switch = spisokForAdapter.get(1).switchFilter
                        switch.isChecked = switch.isChecked != true
                    }
                    2 -> {
                        val switch = spisokForAdapter.get(2).switchFilter
                        switch.isChecked = switch.isChecked != true
                    }
                    3 -> {
                        val switch = spisokForAdapter.get(3).switchFilter
                        switch.isChecked = switch.isChecked != true
                    }
                }
            }
        })
    }

    // инициализация SharedPreferences и Switch
    fun initSwitchandSharedPreferences() {
        pref1 = getSharedPreferences("hranilishe1", MODE_PRIVATE)
        pref2 = getSharedPreferences("hranilishe2", MODE_PRIVATE)
        pref3 = getSharedPreferences("hranilishe3", MODE_PRIVATE)
        pref4 = getSharedPreferences("hranilishe4", MODE_PRIVATE)
        switch = Switch(this)
        switch1 = Switch(this)
        switch2 = Switch(this)
        switch3 = Switch(this)

        // если не нулевые, то обновляем наши Switch. По-дефолту ставим true
        if (pref1 != null && pref2 != null && pref3 != null && pref4 != null) {
            switch.isChecked = pref1.getBoolean("value1", true)
            switch1.isChecked = pref2.getBoolean("value2", true)
            switch2.isChecked = pref3.getBoolean("value3", true)
            switch3.isChecked = pref4.getBoolean("value4", true)
        }
    }

    override fun onPause() {
        super.onPause()
        saveStageActivity() // сохранили состояние текущее в хранилище
        // изменили состояние
        switch.isChecked = pref1.getBoolean("value1", true)
        switch1.isChecked = pref2.getBoolean("value2", true)
        switch2.isChecked = pref3.getBoolean("value3", true)
        switch3.isChecked = pref4.getBoolean("value4", true)
    }

    // забирает последнее состояние Switch, перед onStop
    fun saveStageActivity() {
        val edit1: SharedPreferences.Editor = pref1.edit()
        val edit2: SharedPreferences.Editor = pref2.edit()
        val edit3: SharedPreferences.Editor = pref3.edit()
        val edit4: SharedPreferences.Editor = pref4.edit()
        edit1.putBoolean("value1", spisokForAdapter.get(0).switchFilter.isChecked)
        edit2.putBoolean("value2", spisokForAdapter.get(1).switchFilter.isChecked)
        edit3.putBoolean("value3", spisokForAdapter.get(2).switchFilter.isChecked)
        edit4.putBoolean("value4", spisokForAdapter.get(3).switchFilter.isChecked)
        edit1.apply()
        edit2.apply()
        edit3.apply()
        edit4.apply()
    }

    // чтение файла json (категории в фильтре)
    fun read_json(): List<Kategorii> {
        val json: String
        val jsonList = mutableListOf<Kategorii>()

        try {
            val inputStream: InputStream = assets.open("categorii.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonArrayKategorii = JSONArray(json)

            for (i in 0..jsonArrayKategorii.length() - 1) {
                val jsonObj = jsonArrayKategorii.getJSONObject(i)
                jsonList.add(Kategorii(jsonObj.getString("tip")))
            }
        } catch (e: Exception) {
            // здесь дополнительная обработка при отсутствии файла
            e.stackTrace
        }

        return jsonList
    }

    // список для адаптера RecyclerView
    fun fillList(switch: Switch, switch1: Switch, switch2: Switch, switch3: Switch): List<FilterData> {
        val data = mutableListOf<FilterData>()
        data.add(FilterData(spisokKategorii.get(0).nazvanie, switch))
        data.add(FilterData(spisokKategorii.get(1).nazvanie, switch1))
        data.add(FilterData(spisokKategorii.get(2).nazvanie, switch2))
        data.add(FilterData(spisokKategorii.get(3).nazvanie, switch3))
        return data
    }
}
