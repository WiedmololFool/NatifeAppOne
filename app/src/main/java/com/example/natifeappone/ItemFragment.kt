package com.example.natifeappone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.natifeappone.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    private var itemId: Int? = null
    private var binding: FragmentItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt(Constants.ID_KEY, 404)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentItemBinding.inflate(inflater, container, false)
        this.binding = binding
        val item = itemId?.let { ItemHolder.getItem(it) }
        val itemPreferences = context?.let { ItemPreferences(it) }
        Toast.makeText(context, "Выбран елемент c Id ${itemPreferences?.getId()}", Toast.LENGTH_SHORT).show()
        with(binding) {
            tvId.text = item?.id.toString()
            tvItemName.text = item?.name
            tvItemDescription.text = item?.description
        }
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}