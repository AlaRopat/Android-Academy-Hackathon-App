package com.academy.app.view.spending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import com.academy.app.dependency.DataStorage
import com.academy.app.mvvm.viewModel.ExpenseViewModel
import kotlinx.android.synthetic.main.spending_list_fragment.*

class SpendingListFragment : Fragment() {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var internalAdapter: SpentItemsAdapter


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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadExpenses()
        return inflater.inflate(R.layout.spending_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        internalAdapter = SpentItemsAdapter(emptyList())
        viewModel.expenseList.observe(this, Observer { expenses ->
            internalAdapter.setItems(expenses)
            internalAdapter.notifyDataSetChanged()
        })
        expenses_list.apply {
            adapter = internalAdapter
        }
    }
}