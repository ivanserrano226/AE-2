package com.example.ae_2

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeriesViewModel : ViewModel() {
    // State for holding the list of series
    val series = mutableStateOf<List<Serie>>(emptyList())

    init {
        // Fetch the series as soon as the ViewModel is created
        fetchSeries()
    }

    private fun fetchSeries() {
        viewModelScope.launch {
            val response = getRetroFit().create(SeriesApiService::class.java).getAllSeries()
            if (response.isSuccessful) {
                series.value = response.body()?.series ?: emptyList()
            } else {
                Log.i("DEV", "ERROR")
            }
        }
    }

    private fun getRetroFit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/series")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun showError() {
        //Toast.makeText(this, "Error al cargar series", Toast.LENGTH_SHORT).show()
    }
}