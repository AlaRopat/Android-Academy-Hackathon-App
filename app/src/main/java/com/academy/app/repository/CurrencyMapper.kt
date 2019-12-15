package com.academy.app.repository

import com.academy.app.data.network.dto.CurrenciesDto
import com.academy.app.data.network.dto.CurrencyDto
import com.academy.app.db.model.Currency

class CurrencyMapper {

    fun map(currenciesDto: CurrenciesDto): List<Currency> =
        currenciesDto.currencies.map { map(it) }

    private fun map(currencyDto: CurrencyDto): Currency = Currency(
        name = currencyDto.abbr,
        curID = currencyDto.curID
    )
}