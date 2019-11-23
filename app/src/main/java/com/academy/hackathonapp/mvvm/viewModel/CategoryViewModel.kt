package com.academy.hackathonapp.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.academy.hackathonapp.db.model.Category
import com.academy.hackathonapp.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CategoryViewModel(private var categoryRepository: CategoryRepository) : ViewModel() {

    var categoryList = MutableLiveData<List<Category>>()

    fun loadCategories() {
        viewModelScope.launch {
            val categories =
                withContext(Dispatchers.IO) { categoryRepository.getAllCategories() }

            categoryList.value = categories

        }

    }

    class CategoryViewModelFactory(
        private val categoryRepository: CategoryRepository

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == CategoryViewModel::class.java) {
                @Suppress("UNCHECKED_CAST")
                CategoryViewModel(
                    categoryRepository

                ) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }
}