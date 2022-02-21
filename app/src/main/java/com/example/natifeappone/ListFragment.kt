package com.example.natifeappone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natifeappone.databinding.FragmentListBinding
import com.example.natifeappone.model.Item


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private var itemListAdapter: ItemListAdapter? = null

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
        val itemPreferences = context?.let { ItemPreferences(it) }

        itemListAdapter = ItemListAdapter(object : OnItemClickListener {

            override fun onClickItem(item: Item) {
                itemPreferences?.setId(item.id)
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
        })

        binding?.let {
            with(it.rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemListAdapter
            }
        }
        itemListAdapter?.submitList(ItemHolder.list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        fun newInstance(): ListFragment{
            return ListFragment()
        }
    }
}



