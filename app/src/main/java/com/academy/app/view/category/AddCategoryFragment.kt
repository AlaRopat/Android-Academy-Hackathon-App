package com.academy.app.view.category

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import com.academy.app.db.model.Category
import com.academy.app.dependency.DataStorage
import com.academy.app.mvvm.viewModel.CategoryViewModel
import com.academy.app.view.MainActivity
import kotlinx.android.synthetic.main.add_category_fragment.*

class AddCategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            CategoryViewModel.CategoryViewModelFactory(
                DataStorage.categoryRepository
            )
        ).get(CategoryViewModel::class.java)

        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loadCategories()
        return inflater.inflate(R.layout.add_category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var categoryNameValue = ""
        category_name_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                categoryNameValue = s.toString()
            }
        }).toString()
        val activity = activity as MainActivity
        save_button.setOnClickListener {
            viewModel.addCategory(Category(categoryName = categoryNameValue))
            activity.pop()
        }
    }
}