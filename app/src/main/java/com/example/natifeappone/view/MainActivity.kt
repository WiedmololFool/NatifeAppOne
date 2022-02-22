package com.example.natifeappone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.natifeappone.Constants
import com.example.natifeappone.MyService
import com.example.natifeappone.databinding.ActivityMainBinding
import com.example.natifeappone.presenter.ActivityPresenter

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var activityPresenter: ActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        activityPresenter = ActivityPresenter(
            intent.getIntExtra(Constants.ID_KEY, Constants.ID_DEF_VAL),
            savedInstanceState
        ).apply {
            onViewAttached(this@MainActivity)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        activityPresenter.onViewDetached()
    }

    fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

}