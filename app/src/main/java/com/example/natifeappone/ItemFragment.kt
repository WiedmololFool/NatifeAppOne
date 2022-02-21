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
            itemId = it.getInt(Constants.ID_KEY, Constants.ID_DEF_VAL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentItemBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = itemId?.let { ItemHolder.getItem(it) }
        val itemPreferences = context?.let { ItemPreferences(it) }
        Toast.makeText(context,
            "${getString(R.string.toast_chosen_item_id)} ${itemPreferences?.getId()}",
            Toast.LENGTH_SHORT).show()

        binding?.let {
            with(it) {
                tvId.text = item?.id.toString()
                tvItemName.text = item?.name
                tvItemDescription.text = item?.description
            }
        }
    }

    companion object {

        fun newInstance(id: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ID_KEY, id)
                }
            }
    }
}