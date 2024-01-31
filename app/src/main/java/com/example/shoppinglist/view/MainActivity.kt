package com.example.shoppinglist.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.model.ShopItem
import java.util.LinkedList
import com.example.shoppinglist.model.Unit
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), ItemClickListener {

    private val PREFS_NAME = "MyPrefsFile"
    private val ITEMS_KEY = "items"

    private lateinit var binding : ActivityMainBinding

    private var units = LinkedList<Unit>()

    private var items = LinkedList<ShopItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if there is saved instance state
        if (savedInstanceState != null) {
            items = savedInstanceState.getSerializable("savedItems") as LinkedList<ShopItem>
        } else {
            // If no saved instance state, load items
            loadItems()

            val mainIntent = getIntent()
            val newItem = mainIntent.getSerializableExtra("newItem") as? ShopItem

            if (newItem != null) {
                items.add(newItem)
                saveItems()
            }
        }

        binding.itemRecycler.layoutManager = LinearLayoutManager(this)
        binding.itemRecycler.adapter = ItemAdapter(items, this)

        binding.addItemButton.setOnClickListener {
            startActivity(Intent(this, NewItemActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the items list to the bundle
        outState.putSerializable("savedItems", items)
    }

    private fun loadItems() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val serializedItems = prefs.getString(ITEMS_KEY, null)

        if (!serializedItems.isNullOrBlank()) {
            val type = object : TypeToken<LinkedList<ShopItem>>() {}.type
            items = Gson().fromJson(serializedItems, type) ?: LinkedList()
        }
    }

    private fun saveItems() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val serializedItems = Gson().toJson(items)
        editor.putString(ITEMS_KEY, serializedItems)
        editor.apply()
    }

    override fun onRemoveItemClick(position: Int) {
        items.removeAt(position)
        saveItems()
        binding.itemRecycler.adapter?.notifyItemRemoved(position)
    }
}