package com.academy.app.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.academy.app.data.network.Api
import com.academy.app.data.network.NetworkService
import com.academy.app.data.network.dto.CurrencyDto
import com.academy.app.mvvm.Event

class ActivityViewModel(
) : BaseViewModel() {

    var api: Api = NetworkService.retrofitService()

    val simpleLiveData = MutableLiveData<Event<Any>>()
    val currenciesLiveData = MutableLiveData<Event<List<CurrencyDto>>>()

    fun getUsers() {
        requestWithLiveData(simpleLiveData) {
            api.getUsdToday()
        }
    }

    fun getCurrencies() {
        requestWithLiveData(currenciesLiveData) {
            api.getCurrencies()
        }
    }
}
