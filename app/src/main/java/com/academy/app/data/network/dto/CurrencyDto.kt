package com.academy.app.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDto(
    @Json(name = "Cur_ID")
    val curID: Long,
    @Json(name = "Cur_Code")
    val curCode: String,
    @Json(name = "Cur_Abbreviation")
    val abbr: String,
    @Json(name = "Cur_Scale")
    val scale: Int
)