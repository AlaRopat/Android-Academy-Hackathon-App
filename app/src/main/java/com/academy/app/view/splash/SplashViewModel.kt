package com.academy.app.view.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.academy.app.R.drawable
import com.academy.app.data.network.NetworkService
import com.academy.app.data.network.dto.CurrenciesDto
import com.academy.app.data.network.dto.CurrencyDto
import com.academy.app.db.model.Category
import com.academy.app.dependency.DataStorage
import com.academy.app.mvvm.Event
import com.academy.app.mvvm.Status
import com.academy.app.mvvm.viewModel.BaseViewModel
import com.academy.app.repository.CurrencyMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    private val TAG = "SplashViewModel"
    private val listOf = listOf("BYN", "RUB", "USD", "EUR")

    private val api = NetworkService.retrofitService()
    var data = MutableLiveData<Event<Any>>()

    fun doMagic() {

        val curs = CurrenciesDto(
            listOf(
                CurrencyDto(curID = 1L, abbr = "BYN", curCode = "", scale = 1),
                CurrencyDto(curID = 2L, abbr = "RUB", curCode = "", scale = 1),
                CurrencyDto(curID = 5L, abbr = "USD", curCode = "", scale = 1),
                CurrencyDto(curID = 6L, abbr = "EUR", curCode = "", scale = 1)
            )
        )


        viewModelScope.launch(Dispatchers.IO) {
            DataStorage.createCurrencyRepository()
                .saveCurrencyList(CurrencyMapper().map(curs).filter { listOf.contains(it.name) })
        }
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "doMagic: category created")
            DataStorage.createCategoryRepository().addCategories(
                listOf(
                    Category(categoryName = "Shopping", img = drawable.shopping),
                    Category(categoryName = "Food & Drinks", img = drawable.meal),
                    Category(categoryName = "Transport", img = drawable.transport),
                    Category(categoryName = "Utilities", img = drawable.utilities)
                )
            )
            delay(5000)
            data.postValue(Event(Status.SUCCESS, null, null))
        }
    }
}