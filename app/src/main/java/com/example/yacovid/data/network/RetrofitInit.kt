package com.example.yacovid.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInit {

    private val BASE_URL = "https://covid19-api.com/"

    private var api: Api? = null

    @Synchronized
    fun newApiInstance(): Api {
        if (api == null) {
            api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
        return api!!
    }
}