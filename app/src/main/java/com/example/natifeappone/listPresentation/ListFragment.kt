package com.example.natifeappone.listPresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.databinding.FragmentListBinding
import com.example.natifeappone.itemPresentation.ItemFragment
import com.example.natifeappone.itemPresentation.Item
import com.example.natifeappone.repository.ItemPreferences


class ListFragment : Fragment(), ListView {

    private var binding: FragmentListBinding? = null
    private lateinit var listPresenter: ListPresenter
    private val adapter = ItemListAdapter { item ->
        listPresenter.saveItemId(item.id)
        val itemFragment = ItemFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.ID_KEY, item.id)
            }
        }
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container_view, itemFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listPresenter = ListPresenter(ItemPreferences(requireContext()))
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
        listPresenter.attach(this)
        binding?.apply {
            rcView.adapter = adapter
            rcView.layoutManager = LinearLayoutManager(requireContext())
        }
        listPresenter.getItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        listPresenter.detach()
    }

    override fun showItems(items: List<Item>) {
        adapter.submitList(items)
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}



