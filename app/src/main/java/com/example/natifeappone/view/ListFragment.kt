package com.example.natifeappone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.Constants
import com.example.natifeappone.ItemListAdapter
import com.example.natifeappone.R
import com.example.natifeappone.databinding.FragmentListBinding
import com.example.natifeappone.model.ItemHolder
import com.example.natifeappone.model.ItemPreferences
import com.example.natifeappone.presenter.ListPresenter


class ListFragment : Fragment() {

    var binding: FragmentListBinding? = null
        private set
    private lateinit var listPresenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listPresenter = ListPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPresenter.onViewAttached(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        listPresenter.onViewDetached()
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}



