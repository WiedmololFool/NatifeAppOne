package com.example.natifeappone.itemPresentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    private var binding: FragmentItemBinding? = null
    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            ItemViewModelFactory(arguments?.getInt(Constants.ID_KEY, Constants.ID_DEFAULT_VALUE))
        ).get(ItemViewModel::class.java)
        Log.d(
            "ItemFragment VMFactory",
            arguments?.getInt(
                Constants.ID_KEY,
                Constants.ID_DEFAULT_VALUE
            ).toString()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentItemBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.item.observe(viewLifecycleOwner, Observer { item ->
            showItem(item)
            Log.d("ItemFragment observe", item.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun showItem(item: Item) {
        binding?.let {
            with(it) {
                tvId.text = item.id.toString()
                tvItemName.text = item.name
                tvItemDescription.text = item.description
            }
        }
        Toast.makeText(
            context,
            getString(R.string.toast_chosen_item_id, item.id),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        fun newInstance(id: Int): ItemFragment {
            return ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ID_KEY, id)
                }
            }
        }

    }
}