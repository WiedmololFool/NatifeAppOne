package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        val itemPreferences = ItemPreferences(this)

        list = (0 until 20).map {
            Item(it, "Item $it", "Description of item $it")
        }

        itemListAdapter = ItemListAdapter(object : OnItemClickListener {

            override fun onClickItem(item: Item) {
                val intent = Intent(this@MainActivity, ItemActivity::class.java)
                intent.putExtra(ItemActivity.KEY, item)
                this@MainActivity.startActivity(intent)
                itemPreferences.setId(item.id)
            }
        })

        with(binding.rcView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemListAdapter
        }
        itemListAdapter.submitList(list)
    }
}