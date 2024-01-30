package com.example.shoppinglist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.model.ShopItem

interface ItemClickListener {
    fun onRemoveItemClick(position: Int)
}

class ItemAdapter (val itemList : List<ShopItem>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ItemHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setItemData(itemList[position])
        holder.bindRemoveItemButton(itemClickListener, position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}