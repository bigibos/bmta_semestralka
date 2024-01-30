package com.example.shoppinglist.model

enum class Unit (val short: String, val long: String) {
    // Hmotnost
    KG ("kg", "kilogram"),
    DA ("da", "dekagram"),
    G ("g", "gram"),
    // Objem
    L ("l", "litr"),
    DL ("dl", "decilitr"),
    ML ("ml", "mililitr"),
    // Množství
    KS ("ks", "kus"),
    PCKG ("bal", "balení");

    override fun toString(): String {
        return short
    }
}
