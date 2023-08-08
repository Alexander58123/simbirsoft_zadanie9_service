package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
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
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FilterAdapter

    lateinit var spisokForAdapter: List<FilterData>
    lateinit var spisokKategorii: List<Kategorii>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtr)

        buttonhead = findViewById(R.id.menuCategorii2)
        nav = findViewById(R.id.BottomNavagation)
        arrowBack = findViewById(R.id.arrovBackID)

        // наш список категорий, который спарсили из categorii.json
        spisokKategorii = read_json()

        recyclerView = findViewById(R.id.filtrKetegoriiPomoshi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        spisokForAdapter = fillList()

        adapter = FilterAdapter(spisokForAdapter)
        recyclerView.adapter = adapter // передаем в адаптер наш список с данными

        val switch4: Switch = spisokForAdapter.get(0).switchFilter
        switch4.isChecked = false
        adapter.notifyDataSetChanged()

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

        // слушатель для RecyclerView
        adapter.setOnClickListener(object :
            FilterAdapter.OnClickListener {
            override fun onClick(position: Int, model: FilterData) {
                // в зависимости от позиции переключаем Switch
                when (position) {
                    0 -> {
                        // val holder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(0)
                        // val switch = holder?.itemView?.findViewById<Switch>(R.id.switch2)
                        val switch = model.switchFilter
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                            switch?.isChecked = false
                        } else {
                            switch?.text = "вкл."
                            switch?.isChecked = true
                        }
                    }
                    1 -> {
                        val switch = spisokForAdapter.get(1).switchFilter
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                            switch?.isChecked = false
                        } else {
                            switch?.text = "вкл."
                            switch?.isChecked = true
                        }
                    }
                    2 -> {
                        val switch = spisokForAdapter.get(2).switchFilter
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                            switch?.isChecked = false
                        } else {
                            switch?.text = "вкл."
                            switch?.isChecked = true
                        }
                    }
                    3 -> {
                        val switch = spisokForAdapter.get(3).switchFilter
                        if (switch?.isChecked == true) {
                            switch?.text = "откл."
                            switch?.isChecked = false
                        } else {
                            switch?.text = "вкл."
                            switch?.isChecked = true
                        }
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
            // здесь дополнительная обработка при отсутствии файла
            e.stackTrace
        }

        return jsonList
    }

    // список для добавления в фильтр
    fun fillList(): List<FilterData> {
        var data = mutableListOf<FilterData>()
        var switch = Switch(this)
        switch.isChecked = true
        for (i in 0 until spisokKategorii.size) {
            data.add(FilterData(spisokKategorii.get(i).nazvanie, switch))
        }
        return data
    }

    // метод, который сохраняет состояние FiltrActivity
    // метод вызывается перед тем как уничтожить активность
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val massivBoolean: BooleanArray = booleanArrayOf(
            spisokForAdapter.get(0).switchFilter.isChecked,
            spisokForAdapter.get(1).switchFilter.isChecked,
            spisokForAdapter.get(2).switchFilter.isChecked,
            spisokForAdapter.get(3).switchFilter.isChecked,
        )
        savedInstanceState.putBooleanArray("sostoayniyaSwitch", massivBoolean)
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        // восстанавливаем состояние Switch
//
//        spisokForAdapter.get(0).switchFilter.isChecked =
//            savedInstanceState?.getBooleanArray("sostoayniyaSwitch")?.get(0) ?: false
//        spisokForAdapter.get(1).switchFilter.isChecked =
//            savedInstanceState?.getBooleanArray("sostoayniyaSwitch")?.get(1) ?: false
//    }
}
