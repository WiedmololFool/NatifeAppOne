package com.example.natifeappone.activityPresentation
import android.os.Bundle
import com.example.natifeappone.Constants

interface ActivityView {

    fun addListFragment()
    fun addItemFragment(itemIdFromReceiver: Int)

}

class ActivityPresenter(
    private var itemIdFromReceiver: Int,
) {

    private var view: ActivityView? = null

    fun attach(view: ActivityView) {
        this.view = view
    }

    fun detach() {
        view = null
    }

  fun getFragment(savedInstanceState: Bundle?) {
      if (itemIdFromReceiver != Constants.ID_DEFAULT_VALUE && savedInstanceState == null) {
          view?.addItemFragment(itemIdFromReceiver)
      }
  }

}