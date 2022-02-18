package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.natifeappone.databinding.ActivityItemBinding
import com.example.natifeappone.model.Item

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val itemId = intent.getIntExtra(KEY, 404)
        Log.d(Constants.MY_TAG, "ItemActivity from Intent itemId = $itemId")
        val item = ItemHolder.getItem(itemId)
        val itemPreferences = ItemPreferences(this)
        Log.d(Constants.MY_TAG, "ItemActivity from ItemHolder itemId = ${item?.id.toString()}")
        Toast.makeText(this, "Выбран елемент c Id ${itemPreferences.getId()}", Toast.LENGTH_SHORT).show()
        with(binding) {
            tvId.text = item?.id.toString()
            tvItemName.text = item?.name
            tvItemDescription.text = item?.description
        }
    }

    companion object {

        const val KEY = "itemObject"

    }
}