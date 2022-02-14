package com.example.natifeappone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.natifeappone.databinding.ActivityItemBinding
import com.example.natifeappone.model.Item

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val item = intent.getParcelableExtra<Item>(KEY)
        Log.d("MyLogItemActivity", item.toString())
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