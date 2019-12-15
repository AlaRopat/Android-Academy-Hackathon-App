package com.academy.app.view.intro.money

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pixplicity.easyprefs.library.Prefs

class MoneyViewModel : ViewModel() {
    private val TAG = "MoneyViewModel"

    val currencies = listOf("BYN", "RUB", "USD", "EUR").toTypedArray()

    fun saveChosenCurrency(pos: Int) {
        val currencyToSave = currencies[pos]
        Log.d(TAG, "saveChosenCurrency: $currencyToSave")
        Prefs.putString("default_currency", currencyToSave)
    }

    fun saveBalance(money: Int?) {
        Log.d(TAG, "saveBalance: $money")
        Prefs.putInt("balance", money ?: 0)
    }
}