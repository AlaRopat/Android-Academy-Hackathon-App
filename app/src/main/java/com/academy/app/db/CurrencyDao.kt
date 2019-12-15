package com.academy.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.academy.app.db.model.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency ORDER BY name DESC")
    fun getAll(): List<Currency>

    @Query("SELECT * FROM Currency where name = :name")
    fun getCurrencyByName(name: String): Currency

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencyList: List<Currency>)

}
