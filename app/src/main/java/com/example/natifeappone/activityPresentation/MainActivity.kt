package com.example.natifeappone.activityPresentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.natifeappone.Constants
import com.example.natifeappone.MyService
import com.example.natifeappone.R
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.itemPresentation.ItemFragment
import com.example.natifeappone.listPresentation.ListFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        addListFragment()
        viewModel = ViewModelProvider(
            this,
            ActivityViewModelFactory(
                intent.getIntExtra(
                    Constants.ID_KEY,
                    Constants.ID_DEFAULT_VALUE
                )
            )
        ).get(ActivityViewModel::class.java).apply {
            getFragment(savedInstanceState).observe(this@MainActivity, Observer { itemId ->
                addItemFragment(itemId)
            })
        }
        startService()
    }

     private fun addListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ListFragment.newInstance())
            .commit()
    }

    private fun addItemFragment(itemIdFromReceiver: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                ItemFragment.newInstance(itemIdFromReceiver)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }
}