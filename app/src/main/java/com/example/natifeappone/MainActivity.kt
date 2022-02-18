package com.example.natifeappone

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.model.Item

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var itemListAdapter: ItemListAdapter
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val itemPreferences = ItemPreferences(this)
        startService()
        val itemIdFromReceiver = intent.getIntExtra(ItemActivity.KEY, 404)
        Log.d(Constants.MY_TAG, "onCreate itemId = $itemIdFromReceiver")
        if (itemIdFromReceiver != 404 && savedInstanceState == null) {
            val intent = Intent(this, ItemActivity::class.java).apply {
                putExtra(ItemActivity.KEY, itemIdFromReceiver)
            }
            startActivity(intent)
        }

        itemListAdapter = ItemListAdapter(object : OnItemClickListener {

            override fun onClickItem(item: Item) {
                itemPreferences.setId(item.id)
                val intent = Intent(this@MainActivity, ItemActivity::class.java).apply {
                    putExtra(ItemActivity.KEY, item.id)
                }
                this@MainActivity.startActivity(intent)
            }
        })

        with(binding.rcView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemListAdapter
        }
        itemListAdapter.submitList(ItemHolder.list)
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

}