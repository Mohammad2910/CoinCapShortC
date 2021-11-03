package dk.shortcut.dtudemoapp.model.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseWrapperDto<R>(val data: R, val timestamp: Long)
