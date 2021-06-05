package com.example.yacovid.data.repository

import com.example.yacovid.data.network.Api
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository
import retrofit2.Response
import java.io.IOException

class CountryRepositoryImpl(private val api: Api) : CountryRepository {

    override fun getCountries(): List<Country>? {
        val response: Response<List<Country>> = try {
            api.getCountries().execute()
        } catch (e: IOException) {
            return null
        }
        return if (response.code() == 200) {
            response.body()
        } else {
            null
        }
    }
}