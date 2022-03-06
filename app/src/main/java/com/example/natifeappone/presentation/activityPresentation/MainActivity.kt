package com.example.natifeappone.presentation.activityPresentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.natifeappone.Constants
import com.example.natifeappone.MyService
import com.example.natifeappone.R
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.presentation.itemPresentation.ItemFragment
import com.example.natifeappone.presentation.listPresentation.ListFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ActivityViewModelFactory(
                intent.getIntExtra(
                    Constants.ID_KEY,
                    Constants.ID_DEFAULT_VALUE
                )
            )
        ).get(ActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        addListFragment()
        viewModel.apply {
            itemId.observe(this@MainActivity) { itemId ->
                addItemFragment(itemId)
            }
            if (savedInstanceState == null) getItemFragment()
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