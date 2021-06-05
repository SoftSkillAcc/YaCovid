package com.example.yacovid.presentation

import androidx.lifecycle.*
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.usecase.CountryInteractor

class CountryViewModel(private val interactor: CountryInteractor) : ViewModel(), LifecycleObserver {
    val countryLiveData = MutableLiveData<List<Country>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Thread {
            val list = interactor.getCountries()
            countryLiveData.postValue(list)
        }.start()
    }
}