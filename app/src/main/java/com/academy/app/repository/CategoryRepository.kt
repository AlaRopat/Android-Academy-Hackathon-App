package com.academy.app.repository

import com.academy.app.db.CategoryDao
import com.academy.app.db.model.Category

class CategoryRepository(
    private val categoryDao: CategoryDao
) {
    fun getAllCategories(): List<Category> {
        return categoryDao.getAll()
    }

    fun getAllNameCategories(): List<String> {
        return categoryDao.getAllNames()
    }

    fun addCategory(category: Category) {
        categoryDao.insert(category)
    }

    fun addCategories(categories: List<Category>) {
        categoryDao.insertAll(categories)
    }

    fun getByName(name: String): Category {
        return categoryDao.getByName(name)
    }
}