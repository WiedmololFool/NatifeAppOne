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

    private var list = listOf<Item>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = (0 until 20).map {
            Item(it, "Item $it", "Description of item $it")
        }
        list.map{

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
        list = list + Item(101, "My Item", "My Custom Item")
        itemListAdapter.submitList(list)

    }
}