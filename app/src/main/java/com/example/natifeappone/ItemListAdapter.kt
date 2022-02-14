package com.example.natifeappone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.natifeappone.databinding.ListItemBinding
import com.example.natifeappone.model.Item

class ItemListAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(ItemComparator()) {

   inner class ItemViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Item) = with(binding) {
            tvName.text = item.name
            root.setOnClickListener{
               onItemClickListener.onClickItem(getItem(adapterPosition))
            }
        }
    }

    class ItemComparator: DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }
}