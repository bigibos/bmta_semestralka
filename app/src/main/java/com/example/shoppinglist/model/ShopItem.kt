package com.example.shoppinglist.model

import java.io.Serializable

data class ShopItem (
    var name: String,
    var amount: Float,
    var unit: Unit,
    var count: Int
) : Serializable
