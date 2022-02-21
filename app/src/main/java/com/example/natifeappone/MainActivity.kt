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
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ListFragment.newInstance())
            .commit()

        val itemIdFromReceiver = intent.getIntExtra(Constants.ID_KEY, Constants.ID_DEF_VAL)

        if (itemIdFromReceiver != Constants.ID_DEF_VAL && savedInstanceState == null) {
            Log.d(Constants.MY_TAG, "MainActivity Item Id from Intent = $itemIdFromReceiver")
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    ItemFragment.newInstance(itemIdFromReceiver)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

}