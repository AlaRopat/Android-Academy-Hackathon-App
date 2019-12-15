package com.academy.app.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel: ViewModel() {
    private var stateLiveData = MutableLiveData<StateChart>().apply { value = StateChart.PieChart }

    var lv:LiveData<StateChart> = stateLiveData

    fun changeToPieChartState(){
        if (stateLiveData.value!=StateChart.PieChart) stateLiveData.value=StateChart.PieChart

    }

    fun changeToListChartState(){
        if (stateLiveData.value!=StateChart.ListChart) stateLiveData.value=StateChart.ListChart

    }

    fun changeToAddChartState(){
        if (stateLiveData.value!=StateChart.ToAdd) stateLiveData.value=StateChart.ToAdd

    }


companion object {
    sealed class StateChart() {
        object PieChart : StateChart()
        object ListChart : StateChart()
        object ToAdd : StateChart()
    }
}





}