package com.example.natifeappone.presenter

import android.os.Bundle
import android.util.Log
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.view.ItemFragment
import com.example.natifeappone.view.ListFragment
import com.example.natifeappone.view.MainActivity

class ActivityPresenter(
    private var itemIdFromReceiver: Int,
    private var savedInstanceState: Bundle?,
) {
    private var view: MainActivity? = null

    fun onViewAttached(view: MainActivity) {
        this.view = view
        view.startService()
        view.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ListFragment.newInstance())
            .commit()

        if (itemIdFromReceiver != Constants.ID_DEF_VAL && savedInstanceState == null) {
            Log.d(Constants.MY_TAG, "MainActivity Item Id from Intent = $itemIdFromReceiver")
            view.supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    ItemFragment.newInstance(itemIdFromReceiver)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    fun onViewDetached(){
        view = null
    }

}