package com.academy.app.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CurrencyRate(
    @Json(name = "Cur_Scale")
    val scale: Int,
    @Json(name = "Cur_OfficialRate")
    val officialRate: Float
)