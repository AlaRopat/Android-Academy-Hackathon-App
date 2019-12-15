package com.academy.app.view.intro.money

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import kotlinx.android.synthetic.main.fragment_money.currencyPicker
import kotlinx.android.synthetic.main.fragment_money.et_salary

class MoneyFragment : Fragment() {

    private val viewModel: MoneyViewModel by lazy { ViewModelProviders.of(this).get(MoneyViewModel::class.java) }

    companion object {
        @JvmStatic
        fun newInstance() = MoneyFragment()

        private val TAG = "MoneyFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_money, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        currencyPicker.displayedValues = viewModel.currencies
        setupEditText()
        setupPicker()
    }

    private fun setupEditText() {
        et_salary.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.saveBalance(p0?.toString()?.toInt())
            }
        })
    }

    private fun setupPicker() {
        currencyPicker.refreshByNewDisplayedValues(viewModel.currencies)
        currencyPicker.setOnValueChangedListener { picker, oldVal, newVal -> viewModel.saveChosenCurrency(newVal) }
    }
}
