package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.forEach
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.model.Item

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Item>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (i in 0..19) {
            list.add(Item(i, "Item $i", "Description of item $i"))
        }


        itemListAdapter = ItemListAdapter(object : OnItemClickListener{
            override fun onClickItem(item: Item) {
                val intent = Intent(this@MainActivity, ItemActivity::class.java)
                intent.putExtra(ItemActivity.KEY, item)
                this@MainActivity.startActivity(intent)
                Toast.makeText(this@MainActivity,
                    "Выбран елемент c Id ${item.id}",
                    Toast.LENGTH_SHORT).show()
            }
        })

        with(binding.rcView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemListAdapter
        }
        itemListAdapter.submitList(list)
    }
}