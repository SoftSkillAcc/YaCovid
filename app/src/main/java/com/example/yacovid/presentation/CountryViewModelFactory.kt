package com.example.yacovid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yacovid.domain.usecase.CountryInteractor

class CountryViewModelFactory(private val interactor: CountryInteractor) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}