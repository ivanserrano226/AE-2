package com.example.ae_2

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeriesViewModel : ViewModel() {
    val series = mutableStateOf<List<Serie>>(emptyList())

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    init {
        fetchSeries()
    }

    private fun fetchSeries() {
        viewModelScope.launch {
            try {
                val response: Response<SeriesResponse> = retrofit.create(SeriesApiService::class.java)
                    .getAllSeries("series")

                if (response.isSuccessful) {
                    series.value = response.body() ?: emptyList()
                } else {
                    Log.e("API", "Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Network error: ${e.message}")
            }
        }
    }
}