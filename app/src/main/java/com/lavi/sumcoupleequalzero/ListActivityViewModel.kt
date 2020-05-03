package com.lavi.sumcoupleequalzero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lavi.enums.ItemHeightEnum
import com.lavi.models.MyNumber
import com.lavi.models.NumberInt
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListActivityViewModel: ViewModel() {
    private val progressDataMLD = MutableLiveData<Boolean>()
    private val listOfIntMLD = MutableLiveData<MutableList<MyNumber>>()

    fun getProgressDataMLD(): LiveData<Boolean> = progressDataMLD

    fun getListOfIntMLD(): LiveData<MutableList<MyNumber>> = listOfIntMLD

    fun fetchData() {
        viewModelScope.launch {
            val listNumber = ListActivityModel.fetchData()
                .onStart { progressDataMLD.value = true }
                .onCompletion { progressDataMLD.value = false }
                .catch {exp -> Log.e("${this@ListActivityViewModel::class.java.name}: fetchData()",
                    "Error fetch data: ${exp.message}") }
                .single()
            listOfIntMLD.value = getListWithCalcSumOfPairEqualZero(listNumber)
        }
    }

    private fun getListWithCalcSumOfPairEqualZero(numberInt: NumberInt): MutableList<MyNumber> {
        val list = mutableListOf<MyNumber>()
        val mapNumber = mutableMapOf<Int, Int>()

        for ((index,item) in numberInt.numbers.withIndex()){
            mapNumber[item.number] = index
        }

        for ((index,item) in numberInt.numbers.withIndex()){
            if (mapNumber[-item.number] != null && mapNumber[-item.number] != index){
                list.add(MyNumber(item.number, ItemHeightEnum.BIG))
            }
            else{
                list.add(MyNumber(item.number, ItemHeightEnum.SMALL))
            }
        }
        return list
    }
}