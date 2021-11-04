package com.example.coincapshortc.model.model

data class Asset(
    val id: String,
    val name: String,
    val price: Float,
    val volume24Hr: Float,
    val change24Hr: Float,
)
