package com.lavi.sumcoupleequalzero

import com.lavi.models.NumberInt
import com.lavi.network.RetrofitManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ListActivityModel {
    fun fetchData(): Flow<NumberInt> {
        return flow{
            emit(RetrofitManager.instanceServiceApi.getCountryDetails())
        }
    }
}