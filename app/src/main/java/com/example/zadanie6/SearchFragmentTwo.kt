package com.example.zadanie6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragmentTwo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nashSpisok()
    }

    override fun onPause() {
        super.onPause()
        nashSpisok()
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragmentTwo()
    }

    fun nashSpisok() {
        // выводим наш список (мероприятия, фрагмент1)
        var listView2 = view?.rootView?.findViewById<ListView>(R.id.searchList2)
        val masiveGenerate2 = arrayOf(
            "Благотворительный фонд Алины Кабаевой",
            "Во имя жизни", "Благотворительный фонд В. Потанина", "Детские домики", "Мозаика счастья",
            "Дом с маяком", "Верю в чудо", "Старость в радость", "Все дети могут", "Редкие люди",
        )
        val generateMassive = masiveGenerate2.take(Random.nextInt(1, 10))
        val arrayAdapter: ArrayAdapter<String>
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, generateMassive)
        listView2?.adapter = arrayAdapter
    }
}
