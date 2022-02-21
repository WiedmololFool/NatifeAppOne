package com.example.natifeappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.natifeappone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        startService()

        val itemIdFromReceiver = intent.getIntExtra(Constants.ID_KEY, 404)
        Log.d(Constants.MY_TAG, "MainActivity Item Id from Intent = $itemIdFromReceiver")
        val fragment = if (itemIdFromReceiver != 404 && savedInstanceState == null) {
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ID_KEY, itemIdFromReceiver)
                }
            }
        } else {
            ListFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

}