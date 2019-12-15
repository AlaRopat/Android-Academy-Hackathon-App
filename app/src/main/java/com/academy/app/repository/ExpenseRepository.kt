package com.academy.app.repository

import com.academy.app.db.ExpenseDao
import com.academy.app.db.model.AvarageExpense
import com.academy.app.db.model.Category
import com.academy.app.db.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    fun getAllExpenses(): List<Expense> {
        return expenseDao.getAll()
    }

    fun addExpense(expense: Expense) {
        expenseDao.insert(expense)
    }

    fun getExpense(id: Long) {
        expenseDao.getById(id)
    }

    fun getExpensesByCategory(category: Category): List<Expense> {
        return expenseDao.getExpenseByCategory(category.id)
    }

    fun getStatistics(): List<AvarageExpense> {
        return expenseDao.getStatistics()
    }

    fun getTotal(): Long {
        return expenseDao.getTotal()
    }

}