package com.example.zadanie6

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.io.Serializable
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Сервис создан в onCreate", Toast.LENGTH_SHORT).show()
    }

    // основная логика процесса
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Сервис запущен в onStartCommand", Toast.LENGTH_SHORT).show()

        // список для передачи из service in activity
        var spisokForTransfer: List<NewsData>

        var intentOutput = Intent("Transfer")

        var service: ExecutorService = Executors.newSingleThreadExecutor()
        service.execute {
            Thread.sleep(5000)
            spisokForTransfer = initSpisokNewsData()
            intentOutput.putExtra("nashSpisok", spisokForTransfer as Serializable)
            sendBroadcast(intentOutput)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Сервис остановлен в onDestroy", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

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
            }
            // если пустой список, показываем уведомление
//            if (spisok.isEmpty()) {
//                emptyText.visibility = View.VISIBLE
//            }
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
