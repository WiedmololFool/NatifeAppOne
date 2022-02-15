package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.model.Item

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemPreferences = ItemPreferences(this)

        for (i in 0 until 20){
            ItemHolder.addItem(Item(i, "Item $i", "Description of item $i"))
        }

        itemListAdapter = ItemListAdapter(object : OnItemClickListener {

            override fun onClickItem(item: Item) {
                itemPreferences.setId(item.id)
                val intent = Intent(this@MainActivity, ItemActivity::class.java)
                intent.putExtra(ItemActivity.KEY, item.id)
                this@MainActivity.startActivity(intent)
            }
        })

        with(binding.rcView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemListAdapter
        }
        itemListAdapter.submitList(ItemHolder.list)
    }
}