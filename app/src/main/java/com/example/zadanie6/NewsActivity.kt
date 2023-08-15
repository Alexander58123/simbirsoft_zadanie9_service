package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception

class NewsActivity : AppCompatActivity() {

    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var buttonFilter: ImageView
    lateinit var emptyText: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var spisokForAdapter: List<NewsData>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        buttonhead = findViewById(R.id.menuCategorii)
        nav = findViewById(R.id.BottomNavagation)
        buttonFilter = findViewById(R.id.filterButton)
        emptyText = findViewById(R.id.textForEmptySpisok)

        recyclerView = findViewById(R.id.RecyclerNovosti)
        recyclerView.layoutManager = LinearLayoutManager(this)


        spisokForAdapter = initSpisokNewsData()

        // adapter = NewsAdapter(spisokForAdapter)
        // adapter = NewsAdapter().setData(spisokForAdapter)
        adapter = NewsAdapter()
        adapter.setData(spisokForAdapter)
        recyclerView.adapter = adapter // передаем в адаптер наш список с данными
        adapter.notifyDataSetChanged()

        // открытие меню Помочь
        buttonhead.setOnClickListener {
            val intent = Intent(this@NewsActivity, CategoriiPomoshi::class.java)
            startActivity(intent)
        }

        // открытие меню Фильтр
        buttonFilter.setOnClickListener {
            val intent = Intent(this@NewsActivity, FiltrActivity::class.java)
            startActivity(intent)
        }

        // менюшка BootomMenu
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@NewsActivity, Profile::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@NewsActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            return@setOnItemSelectedListener true
        }
    }

    // ВОТ ЗДЕСЬ УТОЧНИТЬ,ЕСЛИ ИНИЦИАЛИЗИРУЕМ В Create, почему наш список
    // снова обновляется и работает без onRestart
//    override fun onRestart() {
//        super.onRestart()
//        spisokForAdapter = initSpisokNewsData()
//
//        adapter = NewsAdapter(spisokForAdapter)
//        recyclerView.adapter = adapter // передаем в адаптер наш список с данными
//    }

    // инициализация списка для адаптера в RecyclerView
    fun initSpisokNewsData(): List<NewsData> {
        val sharedPreferences1 = getSharedPreferences("hranilishe1", MODE_PRIVATE) // деньгами
        val sharedPreferences2 = getSharedPreferences("hranilishe2", MODE_PRIVATE) // вещами
        val sharedPreferences3 = getSharedPreferences("hranilishe3", MODE_PRIVATE) // Проф. помощью
        val sharedPreferences4 = getSharedPreferences("hranilishe4", MODE_PRIVATE) // Волонтерством



        var spisok = mutableListOf<NewsData>()

        try {
            var jsonObject: JSONObject = JSONObject(JsonDataFromAsset("sobutie.json")) // строку принимает
            var jsonArray: JSONArray = jsonObject.getJSONArray("sobutie")
            for (i in 0 until jsonArray.length()) {
                var sobutieData: JSONObject = jsonArray.getJSONObject(i)

                // проверяем наш фильтр перед добавлением
                when (sobutieData.getString("categoriya")) {
                    "Деньгами" -> {
                        if (sharedPreferences1.getBoolean("value1", true) == true) {
                            spisok.add(
                                NewsData(
                                    sobutieData.getInt("id"),
                                    resources.getIdentifier(sobutieData.getString("kartinka"), "drawable", packageName),
                                    sobutieData.getString("title"),
                                    sobutieData.getString("description"),
                                    sobutieData.getString("data"),
                                ),
                            )
                        }
                    }
                    "Вещами" -> {
                        if (sharedPreferences2.getBoolean("value2", true) == true) {
                            spisok.add(
                                NewsData(
                                    sobutieData.getInt("id"),
                                    resources.getIdentifier(sobutieData.getString("kartinka"), "drawable", packageName),
                                    sobutieData.getString("title"),
                                    sobutieData.getString("description"),
                                    sobutieData.getString("data"),
                                ),
                            )
                        }
                    }
                    "Проф. помощью" -> {
                        if (sharedPreferences3.getBoolean("value3", true) == true) {
                            spisok.add(
                                NewsData(
                                    sobutieData.getInt("id"),
                                    resources.getIdentifier(sobutieData.getString("kartinka"), "drawable", packageName),
                                    sobutieData.getString("title"),
                                    sobutieData.getString("description"),
                                    sobutieData.getString("data"),
                                ),
                            )
                        }
                    }
                    "Волонтерством" -> {
                        if (sharedPreferences4.getBoolean("value4", true) == true) {
                            spisok.add(
                                NewsData(
                                    sobutieData.getInt("id"),
                                    resources.getIdentifier(sobutieData.getString("kartinka"), "drawable", packageName),
                                    sobutieData.getString("title"),
                                    sobutieData.getString("description"),
                                    sobutieData.getString("data"),
                                ),
                            )
                        }
                    }
                }

//                spisok.add(
//                    NewsData(
//                        resources.getIdentifier(sobutieData.getString("kartinka"), "drawable", packageName),
//                        sobutieData.getString("title"),
//                        sobutieData.getString("description"),
//                        sobutieData.getString("data"),
//                    ),
//                )
            }
            // если пустой список, показываем уведомление
            if (spisok.isEmpty()) {
                emptyText.visibility = View.VISIBLE
            }
        } catch (ex: Exception) {
            ex.stackTrace
        }

        return spisok
    }

    // метод считываем весь файл json (байты), возвращаем String
    fun JsonDataFromAsset(filename: String): String? {
        var json: String? = null
        try {
            var inputStream: InputStream = assets.open(filename)
            var sizeOfFile: Int = inputStream.available()
            var bufferData = ByteArray(sizeOfFile)
            inputStream.read(bufferData)
            inputStream.close()
            json = String(bufferData, Charsets.UTF_8)
        } catch (ex: Exception) {
            ex.stackTrace
        }
        return json
    }


}
