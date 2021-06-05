package com.example.yacovid.domain.repository

import com.example.yacovid.domain.model.Country

interface CountryRepository {
    fun getCountries(): List<Country>?
}