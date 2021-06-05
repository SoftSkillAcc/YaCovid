package com.example.yacovid.domain.usecase

import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository

class CountryInteractor(private val repository: CountryRepository) {

    fun getCountries(): List<Country> {
        return repository.getCountries() ?: return listOf()
    }
}