package com.example.natifeappone.presentation.listPresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.databinding.FragmentListBinding
import com.example.natifeappone.presentation.itemPresentation.ItemFragment


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(viewModelStore, ListViewModelFactory(requireContext()))
            .get(ListViewModel::class.java)
    }
    private val adapter = ItemListAdapter { item ->
        viewModel.saveItemId(item.id)
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
        binding?.apply {
            rcView.adapter = adapter
            rcView.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}



