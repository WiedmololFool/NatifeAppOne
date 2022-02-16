package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.model.Item

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        notificationManager = NotificationManagerCompat.from(this)
        val itemPreferences = ItemPreferences(this)

        ItemHolder.initItems()

        itemListAdapter = ItemListAdapter(object : OnItemClickListener {

            override fun onClickItem(item: Item) {
                itemPreferences.setId(item.id)
                startService(itemPreferences.getId())
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

    private fun startService(itemId: Int) {
        val serviceIntent = Intent(this, MyService::class.java)
        serviceIntent.putExtra(MyService.SERVICE_KEY, itemId)
        startService(serviceIntent)
    }

}