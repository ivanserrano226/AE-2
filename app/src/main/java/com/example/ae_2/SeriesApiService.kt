package com.example.ae_2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SeriesApiService {
    @GET
    suspend fun getAllSeries() : Response<SeriesResponse>
}