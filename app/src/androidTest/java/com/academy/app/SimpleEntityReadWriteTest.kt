package com.academy.app

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.app.db.CategoryDao
import com.academy.app.db.CurrencyDao
import com.academy.app.db.ExpenseDao
import com.academy.app.db.TestDatabase
import com.academy.app.db.model.Category
import com.academy.app.db.model.Currency
import com.academy.app.db.model.Expense
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var expenseDao: ExpenseDao
    private lateinit var currencyDao: CurrencyDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: TestDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TestDatabase::class.java
        ).build()
        expenseDao = db.expenseDao()
        categoryDao = db.categoryDao()
        currencyDao = db.currencyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeExpenseAndReadInList() {

        val expense: Expense = Expense().apply {
            expenseSum = 2L
            description = "Test"
            date = System.currentTimeMillis()
        }
        expenseDao.insert(expense)
        val list: List<Expense> = expenseDao.getAll()

        assertTrue(list.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun writeCategoryAndReadInList() {

        val category: Category = Category().apply {
            categoryName = "Test Category"
        }
        categoryDao.insert(category)
        val list: List<Category> = categoryDao.getAll()

        assertTrue(list.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun addCategoriesAndReadInList() {

        val testCategory: Category = Category().apply {
            categoryName = "Test Category"
        }

        val abcCategory: Category = Category().apply {
            categoryName = "abc category"
        }
        categoryDao.insertAll(listOf(testCategory, abcCategory))
        val list: List<Category> = categoryDao.getAll()

        assertTrue(list.size == 2)
    }

    @Test
    @Throws(Exception::class)
    fun writeCurrencyAndReadInList() {

        val currency: Currency = Currency().apply {
            name = "Test currency"
        }
        currencyDao.insertAll(listOf(currency))
        val list: List<Currency> = currencyDao.getAll()

        assertTrue(list.isNotEmpty())
    }
}