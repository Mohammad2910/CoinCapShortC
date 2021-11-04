package com.example.coincapshortc.model.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.coincapshortc.model.model.Asset

@JsonClass(generateAdapter = true)
data class AssetDto(
    val id: String,
    val name: String,
    @Json(name = "priceUsd")
    val price: Float,
    @Json(name = "volumeUsd24Hr")
    val volume24Hr: Float,
    @Json(name = "changePercent24Hr")
    val change24Hr: Float,
)

fun AssetDto.toModel(): Asset =
    Asset(id, name, price, volume24Hr, change24Hr)

