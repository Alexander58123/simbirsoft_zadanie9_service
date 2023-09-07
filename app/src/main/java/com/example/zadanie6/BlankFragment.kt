package com.example.zadanie6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {

    private lateinit var adapter: ItemPomochAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemArrayList: ArrayList<ItemPomoch>

    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView = view.findViewById(R.id.rcView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ItemPomochAdapter(itemArrayList)
        recyclerView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // устаревший метод, закомментил и не использовал
        // retainInstance = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key_save_state", itemArrayList as Serializable)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            itemArrayList = savedInstanceState.getSerializable("key_save_state") as ArrayList<ItemPomoch>
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun dataInitialize() {
        itemArrayList = arrayListOf<ItemPomoch>()

        imageId = arrayOf(
            R.drawable.pomoch1,
            R.drawable.pomoch2,
            R.drawable.pomoch3,
            R.drawable.pomoch4,
            R.drawable.pomoch5,
        )

        heading = arrayOf(
            "Дети",
            "Взрослые",
            "Пожилые",
            "Животные",
            "Мероприятия",
        )

        for (i in imageId.indices) {
            val items = ItemPomoch(imageId[i], heading[i])
            itemArrayList.add(items)
        }
    }
}
