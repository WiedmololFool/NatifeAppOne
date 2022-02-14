package com.example.natifeappone

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.natifeappone.databinding.ListItemBinding
import com.example.natifeappone.model.Item

class ItemAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var itemsList = ArrayList<Item>()

    companion object {
        var viewHolderCount: Int = 0
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onClickItem(itemsList[adapterPosition])
            }
        }

        fun bindItem(item: Item) {
            binding.tvName.text = item.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        Log.d("MyLogRecyclerView", "onCreateViewHolder, ViewHolderIndex: $viewHolderCount")
        viewHolderCount++
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(itemsList[position])
        Log.d("MyLogRecyclerView", "Pos: $position : ${itemsList[position]}")
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun addItem(item: Item) {
        itemsList.add(item)
        notifyDataSetChanged()
    }

}