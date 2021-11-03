package dk.shortcut.dtudemoapp.model.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RateDto(
    val id: String? ,
    val symbol: String?,
    val currencySymbol: String?,
    val type: String?,
    val rateUsd: String?,
)