package com.example.natifeappone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.model.Item

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Item>()
    private lateinit var binding: ActivityMainBinding
    private val itemAdapter = ItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (i in 0..19) {
            list.add(Item(i, "Item $i", "Description of item $i"))
        }

        with(binding.rcView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
            for (i in 0 until list.size) itemAdapter.addItem(list[i])
        }

    }
}