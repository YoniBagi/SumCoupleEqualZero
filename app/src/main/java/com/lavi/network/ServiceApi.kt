package com.lavi.network

import com.lavi.models.NumberInt
import retrofit2.http.GET

interface ServiceApi {
    @GET("8wJzytQX")
    suspend fun getCountryDetails() : NumberInt
}