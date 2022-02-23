package com.example.natifeappone.activityPresentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.natifeappone.Constants
import com.example.natifeappone.MyService
import com.example.natifeappone.R
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.itemPresentation.ItemFragment
import com.example.natifeappone.listPresentation.ListFragment

class MainActivity : AppCompatActivity(), ActivityView {

    private var binding: ActivityMainBinding? = null
    private lateinit var activityPresenter: ActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        addListFragment()
        activityPresenter = ActivityPresenter(
            intent.getIntExtra(Constants.ID_KEY, Constants.ID_DEFAULT_VALUE)
        ).apply {
            attach(this@MainActivity)
            getFragment(savedInstanceState)
        }
        startService()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityPresenter.detach()
    }

    override fun addListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ListFragment.newInstance())
            .commit()
    }

    override fun addItemFragment(itemIdFromReceiver: Int) {
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