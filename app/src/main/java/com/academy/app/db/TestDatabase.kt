package com.academy.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.academy.app.db.model.Currency
import com.academy.app.db.model.Expense
import com.academy.app.db.model.Category

@Database(
    entities = [Category::class, Expense::class, Currency::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TestDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun currencyDao(): CurrencyDao
}