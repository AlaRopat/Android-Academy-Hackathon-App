package com.academy.app.repository

import com.academy.app.db.CurrencyDao
import com.academy.app.db.model.Currency

class CurrencyRepository(private val currencyDao: CurrencyDao) {

    fun getAll(): List<Currency> {
        return currencyDao.getAll()
    }

    fun saveCurrencyList(currenciesList: List<Currency>) {
        return currencyDao.insertAll(currenciesList)
    }

    fun getByName(name: String): Currency {
        return currencyDao.getCurrencyByName(name)
    }
}