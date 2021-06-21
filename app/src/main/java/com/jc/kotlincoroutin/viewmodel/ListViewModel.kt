package com.jc.kotlincoroutin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jc.kotlincoroutin.model.CountriesService
import com.jc.kotlincoroutin.model.Country
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    private val mException =
        CoroutineExceptionHandler { coroutineContext, throwable -> onError("Exception: ${throwable.localizedMessage}") }
    private val countriesService = CountriesService.getCountriesService()
    var job: Job? = null


    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + mException).launch {
            var response = countriesService.getCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    countryLoadError.value = ""
                    loading.value = false
                } else {
                    onError("Exception: ${response.message()}")
                }
            }
        }

    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
