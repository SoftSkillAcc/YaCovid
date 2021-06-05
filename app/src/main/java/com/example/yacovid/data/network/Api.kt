package com.example.yacovid.data.network

import com.example.yacovid.domain.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @Headers("Accept: application/json")
    @GET("country/all?format=json")
    fun getCountries(): Call<List<Country>>
}