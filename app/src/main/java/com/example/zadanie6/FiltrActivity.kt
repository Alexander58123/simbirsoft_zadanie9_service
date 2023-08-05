package com.example.zadanie6

import android.content.Intent
import android.os.Bundle
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
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FilterAdapter

    lateinit var spisokKategorii: List<Kategorii>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtr)

        buttonhead = findViewById(R.id.menuCategorii2)
        nav = findViewById(R.id.BottomNavagation)

        // наш список категорий, который спарсили из categorii.json
        spisokKategorii = read_json()

        recyclerView = findViewById(R.id.filtrKetegoriiPomoshi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FilterAdapter(fillList())
        recyclerView.adapter = adapter // передаем в адаптер наш список с данными

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@FiltrActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

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

        // слушатель для RecyclerView
        adapter.setOnClickListener(object :
            FilterAdapter.OnClickListener {
            override fun onClick(position: Int, model: FilterData) {
                // в зависимости от позиции переключаем Switch
                when (position) {
                    0 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(0)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    1 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(1)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    2 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(2)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                    3 -> {
                        val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(3)
                        val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                        } else {
                            switch?.text = "вкл."
                        }

                        switch?.isChecked = switch?.isChecked != true
                    }
                }
            }
        })

    }

    // чтение файла json (категории)
    fun read_json(): List<Kategorii> {
        var json: String? = null
        var jsonList = mutableListOf<Kategorii>()

        try {
            val inputStream: InputStream = assets.open("categorii.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonArrayKategorii = JSONArray(json)

            for (i in 0..jsonArrayKategorii.length() - 1) {
                var jsonObj = jsonArrayKategorii.getJSONObject(i)
                jsonList?.add(Kategorii(jsonObj.getString("tip")))
            }

            //      проверка вывода списка
//            var adapterList: ArrayAdapter<Kategorii> = ArrayAdapter(this, android.R.layout.simple_list_item_1, jsonList)
//            var listTest = findViewById<ListView>(R.id.test)
//            listTest.adapter = adapterList
        } catch (e: Exception) {
            e.stackTrace
        }

        return jsonList
    }

    // список для добавления в фильтр
    fun fillList(): List<FilterData> {
        val data = mutableListOf<FilterData>()
//        data.add(FilterData(spisokKategorii.get(0).nazvanie, Switch(this)))
//        data.add(FilterData(spisokKategorii.get(1).nazvanie, Switch(this)))
//        data.add(FilterData(spisokKategorii.get(2).nazvanie, Switch(this)))
//        data.add(FilterData(spisokKategorii.get(3).nazvanie, Switch(this)))

        for (i in 0 until spisokKategorii.size) {
            data.add(FilterData(spisokKategorii.get(i).nazvanie, Switch(this)))
        }
        return data
    }
}
