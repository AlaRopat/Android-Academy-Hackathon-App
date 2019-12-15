package com.academy.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.academy.app.db.model.AvarageExpense
import com.academy.app.db.model.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM Expense ORDER BY date DESC")
    fun getAll(): List<Expense>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expense: Expense)

    @Query("SELECT * FROM Expense WHERE id = :id")
    fun getById(id: Long): Expense?

    @Query("SELECT * FROM Expense WHERE category_id = :categoryId")
    fun getExpenseByCategory(categoryId: Long): List<Expense>

    @Query("SELECT SUM(expenseSum) as total, category_categoryName as categoryName FROM Expense GROUP BY category_categoryName")
    fun getStatistics(): List<AvarageExpense>

    @Query("SELECT SUM(expenseSum) as total FROM Expense")
    fun getTotal(): Long

}
