package com.example.shoppinglist.view

import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.model.ShopItem
import java.text.FieldPosition

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var itemName : TextView?
    private var itemCount : TextView?
    private var itemAmount : TextView?
    private var itemUnit : TextView?

    init {
        itemName = view.findViewById(R.id.itemName)
        itemCount = view.findViewById(R.id.itemCount)
        itemAmount = view.findViewById(R.id.itemAmount)
        itemUnit = view.findViewById(R.id.itemUnit)
    }

    fun setItemData(item: ShopItem) {
        itemName?.text = item.name
        itemCount?.text = item.count.toString()
        itemAmount?.text = item.amount.toString()
        itemUnit?.text = item.unit.short
    }

    fun bindRemoveItemButton(itemClickListener: ItemClickListener, position: Int) {
        val removeItemButton: Button = itemView.findViewById(R.id.removeItemButton)

        removeItemButton.setOnClickListener {
            itemClickListener.onRemoveItemClick(position)
        }
    }
}