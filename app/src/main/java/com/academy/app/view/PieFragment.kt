package com.academy.app.view

import android.graphics.Color
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
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_pie_chart.*

class PieFragment:Fragment() {

    private lateinit var viewModel: ExpenseViewModel

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
    ): View? {
        viewModel.loadStatistics()
        return inflater.inflate(R.layout.fragment_pie_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.statistics.observe(this, Observer { stat ->
            val data = PieDataSet(
                stat.map { item -> PieEntry(item.percentage, item.name) },
                "Our spending"
            )
            data.colors = ColorTemplate.createColors(JOYFUL_COLORS)
            data.valueTextSize = 15f
            pieChartId.data = PieData(data)
            pieChartId.setEntryLabelTextSize(15F)
            pieChartId.setCenterTextSize(15F)
            pieChartId.setEntryLabelColor(Color.BLACK)
            pieChartId.centerText = "Statistics per category"
            pieChartId.animateXY(1000, 1500)
        })

    }

    val JOYFUL_COLORS = intArrayOf(
        Color.rgb(217, 80, 138),
        Color.rgb(254, 149, 7),
        Color.rgb(254, 247, 120),
        Color.rgb(106, 167, 134),
        Color.rgb(53, 194, 209),
        Color.rgb(124, 252, 0),
        Color.rgb(64, 224, 208),
        Color.rgb(25, 25, 112),
        Color.rgb(139, 0, 139),
        Color.rgb(255, 20, 147)

    )

}