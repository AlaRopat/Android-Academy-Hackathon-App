package com.academy.app.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrenciesDto(
    val currencies: List<CurrencyDto>
)