package com.academy.app.dependency

import androidx.room.Room
import com.academy.app.App
import com.academy.app.repository.CategoryRepository
import com.academy.app.repository.CurrencyRepository
import com.academy.app.repository.ExpenseRepository
import com.example.myapplication.db.AppDatabase

object DataStorage {
    val database by lazy {
        createDatabase()
    }

    val categoryRepository by lazy {
        createCategoryRepository()
    }

    val expenseRepository by lazy {
        createExpenseRepository()
    }

    val currencyRepository by lazy {
        createCurrencyRepository()
    }

    private fun createDatabase(): AppDatabase {
        return Room.databaseBuilder(
            App.instance,
            AppDatabase::class.java, "financial_assistant.db"
        ).build()
    }

    fun createCategoryRepository(): CategoryRepository {
        return CategoryRepository(database.categoryDao())
    }

    fun createExpenseRepository(): ExpenseRepository {
        return ExpenseRepository(database.expenseDao())
    }

    fun createCurrencyRepository(): CurrencyRepository {
        return CurrencyRepository(database.currencyDao())
    }
}