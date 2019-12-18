package com.academy.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import com.academy.app.db.model.Expense
import com.academy.app.dependency.DataStorage
import com.academy.app.mvvm.viewModel.CategoryViewModel
import com.academy.app.mvvm.viewModel.ExpenseViewModel
import kotlinx.android.synthetic.main.adding_item_fragment.*


class AddNewItemFragment : Fragment() {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ExpenseViewModel.ExpenseViewModelFactory(
                DataStorage.expenseRepository,
                DataStorage.categoryRepository,
                DataStorage.currencyRepository
            )
        ).get(ExpenseViewModel::class.java)
        categoryViewModel = ViewModelProviders.of(
            this,
            CategoryViewModel.CategoryViewModelFactory(
                DataStorage.categoryRepository
            )
        ).get(CategoryViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryViewModel.loadCategories()
        return layoutInflater.inflate(R.layout.adding_item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryViewModel.categoryList.observe(this, Observer { categories ->
            categories_spinner.adapter = ArrayAdapter<String>(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                categories.map { it.categoryName }
            )
        })

        save_spend.setOnClickListener {
            val expense = Expense(
                description = input_description.text.toString(),
                expenseSum = sumSpend.text.toString().toLong(),
                date = System.currentTimeMillis()
            )
            viewModel.addExpense(expense, categories_spinner.selectedItem.toString())
            val activity = activity as MainActivity
            activity.showFragment(MainFragment(), false)
        }


    }


}