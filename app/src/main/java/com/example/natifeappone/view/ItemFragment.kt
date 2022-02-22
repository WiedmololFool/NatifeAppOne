package com.example.natifeappone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.natifeappone.Constants
import com.example.natifeappone.R
import com.example.natifeappone.databinding.FragmentItemBinding
import com.example.natifeappone.model.Item
import com.example.natifeappone.presenter.ItemPresenter

class ItemFragment : Fragment() {

    private var binding: FragmentItemBinding? = null
    private lateinit var itemPresenter: ItemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemPresenter = ItemPresenter(arguments?.getInt(Constants.ID_KEY, Constants.ID_DEF_VAL))
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
        itemPresenter.onViewAttached(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        itemPresenter.onViewDetached()
    }

    fun showItem(item: Item?) {
        binding?.let {
            with(it) {
                tvId.text = item?.id.toString()
                tvItemName.text = item?.name
                tvItemDescription.text = item?.description
            }
        }
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