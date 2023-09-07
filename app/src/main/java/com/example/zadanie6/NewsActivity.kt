package com.example.zadanie6

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.Serializable

class NewsActivity : AppCompatActivity() {

    lateinit var nav: BottomNavigationView
    lateinit var buttonhead: ExtendedFloatingActionButton
    lateinit var buttonFilter: ImageView
    lateinit var emptyText: TextView
    lateinit var progressBar: ProgressBar

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var spisokForAdapter: List<NewsData>
    // lateinit var mListState: Parcelable

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        buttonhead = findViewById(R.id.menuCategorii)
        nav = findViewById(R.id.BottomNavagation)
        buttonFilter = findViewById(R.id.filterButton)
        emptyText = findViewById(R.id.textForEmptySpisok)
        progressBar = findViewById(R.id.progressBar)

        recyclerView = findViewById(R.id.RecyclerNovosti)
        recyclerView.layoutManager = LinearLayoutManager(this)

        spisokForAdapter = emptyList()
        adapter = NewsAdapter()

        progressBar.visibility = View.VISIBLE

        if (savedInstanceState != null) {
            progressBar.visibility = View.GONE
            spisokForAdapter = savedInstanceState.getSerializable("key_state") as List<NewsData>
            adapter.setData(spisokForAdapter)
            recyclerView.adapter = adapter // передаем в адаптер наш список с данными
            adapter.notifyDataSetChanged()

            //  слушатель для адаптера через анонимный класс
            adapter.setOnClickListener(object : NewsAdapter.OnClickListener {
                override fun onClick(position: Int, model: NewsData) {
                    val tekushiiObject = spisokForAdapter[position]
                    val intent = Intent(this@NewsActivity, SobutiePodrobno::class.java)
                    intent.putExtra("sobutieData", tekushiiObject)
                    startActivity(intent)
                }
            })
        } else {
            // вызываем сервис
            val intent: Intent = Intent(applicationContext, MyService::class.java)
            startService(intent)

            // получаем данные из service
            val intentFilter: IntentFilter = IntentFilter()
            intentFilter.addAction("Transfer")

            val broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    if (intent?.action.equals("Transfer")) {
                        var spisokFromTransferService =
                            intent?.getSerializableExtra("nashSpisok") as List<NewsData>
                        spisokForAdapter = spisokFromTransferService
                        adapter.setData(spisokForAdapter)
                        recyclerView.adapter = adapter // передаем в адаптер наш список с данными
                        adapter.notifyDataSetChanged()

                        //  слушатель для адаптера через анонимный класс
                        adapter.setOnClickListener(object : NewsAdapter.OnClickListener {
                            override fun onClick(position: Int, model: NewsData) {
                                val tekushiiObject = spisokForAdapter[position]
                                val intent = Intent(this@NewsActivity, SobutiePodrobno::class.java)
                                intent.putExtra("sobutieData", tekushiiObject)
                                startActivity(intent)
                            }
                        })

                        progressBar.visibility = View.GONE
                    }
                }
            }

            registerReceiver(broadcastReceiver, intentFilter)


        }

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

    // сохраняем состояние при повороте
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key_state", spisokForAdapter as Serializable)
    }


}
