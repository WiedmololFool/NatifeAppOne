package com.example.natifeappone.presentation.itemPresentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.data.models.Item
import com.example.natifeappone.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    private var binding: FragmentItemBinding? = null
    private val viewModel by lazy {
        val itemId = arguments?.getInt(
            Constants.ID_KEY,
            Constants.ID_DEFAULT_VALUE
        ) ?: Constants.ID_DEFAULT_VALUE
        ViewModelProvider(
            viewModelStore,
            ItemViewModelFactory(itemId)
        ).get(ItemViewModel::class.java)
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
            viewModel.item.observe(viewLifecycleOwner) { item ->
                item.fold(
                    { showItem(item.getOrThrow()) },
                    { showNoItem() }
                )
                Log.d("ItemFragment observe", item.toString())
            }
            viewModel.validateItemId()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showItem(item: Item) {
        binding?.apply {
            root.visibility = View.VISIBLE
            tvId.text = item.id.toString()
            tvItemName.text = item.name
            tvItemDescription.text = item.description
        }
        Toast.makeText(
            context,
            getString(R.string.toast_chosen_item_id, item.id),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showNoItem() {
        binding?.root?.visibility = View.INVISIBLE
        Toast.makeText(
            context,
            getString(R.string.toast_no_item),
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