package com.example.shoppinglist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityNewItemBinding
import com.example.shoppinglist.model.ShopItem
import com.example.shoppinglist.model.Unit

class NewItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewItemBinding

    var itemName: String
    var itemCount: Int
    var itemAmount: Float
    var itemUnit: Unit

    init {
        itemName = ""
        itemAmount = 0f
        itemCount = 1
        itemUnit = Unit.values().first()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.confirmButton.setOnClickListener {
            val newItem = ShopItem(itemName, itemAmount, itemUnit, itemCount)
            val newItemIntent = Intent(this, MainActivity::class.java)
            newItemIntent.putExtra("newItem", newItem)
            startActivity(newItemIntent)
            // startActivity(Intent(this, MainActivity::class.java))
        }

        binding.itemNameInput.setText(itemName)
        binding.itemAmountInput.setText(itemAmount.toString())
        binding.itemCountInput.setText(itemCount.toString())

        binding.itemUnitSpinner.setAdapter(ArrayAdapter<Unit>(this, android.R.layout.simple_spinner_item, Unit.values()))

        binding.itemNameInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                itemName = s.toString()
            }
        })

        binding.itemAmountInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                itemAmount = s.toString().toFloatOrNull() ?: 0f
            }
        })

        binding.itemCountInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                itemCount = s.toString().toIntOrNull() ?: 1
            }
        })

        binding.itemUnitSpinner.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                itemUnit = Unit.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        })
    }
}