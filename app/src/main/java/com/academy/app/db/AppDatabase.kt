package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.academy.app.db.CategoryDao
import com.academy.app.db.Converters
import com.academy.app.db.CurrencyDao
import com.academy.app.db.ExpenseDao
import com.academy.app.db.model.Currency
import com.academy.app.db.model.Expense
import com.academy.app.db.model.Category

@Database(
    entities = [Category::class, Expense::class, Currency::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun currencyDao(): CurrencyDao
}