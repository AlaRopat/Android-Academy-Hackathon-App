package com.academy.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.academy.app.db.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category ORDER BY categoryName ASC")
    fun getAll(): List<Category>

    @Query("SELECT categoryName FROM Category ORDER BY categoryName DESC")
    fun getAllNames(): List<String>

    @Query("SELECT * FROM Category where categoryName = :categoryName")
    fun getByName(categoryName: String): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getById(id: Long): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)

}