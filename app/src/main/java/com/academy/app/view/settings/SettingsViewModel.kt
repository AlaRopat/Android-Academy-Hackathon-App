package com.academy.hackathonapp.view.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.academy.app.repository.CategoryRepository
import com.academy.app.repository.ExpenseRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File

class SettingsViewModel(
    private val categoryRepository: CategoryRepository,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    companion object {

        private const val CATEGORIES_JSON = "categoriesJson.txt"
        private const val EXPENSES_JSON = "expensesJson.txt"
    }

    private val gson = Gson()

    var categoryList = MutableLiveData<Pair<File, File>>()

    fun uploadBackup(filesDir: File) {
        viewModelScope.launch(IO) {
            val allCategories = categoryRepository.getAllCategories()
            val allExpenses = expenseRepository.getAllExpenses()

            val categoriesJson = gson.toJson(allCategories)
            val expensesJson = gson.toJson(allExpenses)

            val categoriesFile = File(filesDir, CATEGORIES_JSON)
            categoriesFile.createNewFile()
            categoriesFile.writeText(categoriesJson)

            val expensesFile = File(filesDir, EXPENSES_JSON)
            expensesFile.createNewFile()
            expensesFile.writeText(expensesJson)

            categoryList.postValue(Pair(categoriesFile, expensesFile))
        }
    }

    class SettingsViewModelFactory(
        private val categoryRepository: CategoryRepository,
        private val expenseRepository: ExpenseRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == SettingsViewModel::class.java) {
                @Suppress("UNCHECKED_CAST")
                SettingsViewModel(categoryRepository, expenseRepository) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }
}