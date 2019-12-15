package com.academy.app.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.academy.app.db.model.Expense
import com.academy.app.db.model.Statistics
import com.academy.app.repository.CategoryRepository
import com.academy.app.repository.CurrencyRepository
import com.academy.app.repository.ExpenseRepository
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpenseViewModel(
    private val expenseRepository: ExpenseRepository,
    private val categoryRepository: CategoryRepository,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    var expenseList = MutableLiveData<List<Expense>>()
    var statistics = MutableLiveData<List<Statistics>>()

    fun loadExpenses() {
        viewModelScope.launch {
            val expenses =
                withContext(Dispatchers.IO) { expenseRepository.getAllExpenses() }

            expenseList.postValue(expenses)

        }

    }

    fun addExpense(expense: Expense, categoryName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val category = categoryRepository.getByName(categoryName)
                expense.category = category
                val currency = currencyRepository.getByName(
                    Prefs.getString("default_currency", "BYN")
                )
                expense.currency = currency
                expenseRepository.addExpense(expense)
            }
        }

    }

    fun loadStatistics() {
        viewModelScope.launch {
            val expenses =
                withContext(Dispatchers.IO) { expenseRepository.getStatistics() }
            val total = withContext(Dispatchers.IO) { expenseRepository.getTotal() }

            statistics.postValue(expenses.map { item ->
                Statistics(
                    (item.total.div(total.toFloat()) * 100),
                    item.categoryName
                )
            })

        }

    }


    class ExpenseViewModelFactory(
        private val expenseRepository: ExpenseRepository,
        private val categoryRepository: CategoryRepository,
        private val currencyRepository: CurrencyRepository

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == ExpenseViewModel::class.java) {
                @Suppress("UNCHECKED_CAST")
                ExpenseViewModel(
                    expenseRepository,
                    categoryRepository,
                    currencyRepository

                ) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }
}