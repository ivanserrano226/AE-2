package com.example.ae_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ae_2.ui.theme.AE2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AE2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun getRetroFit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/series")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun fetchSeries() {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<SeriesResponse> = getRetroFit()
                .create(SeriesApiService::class.java)
                .getAllSeries()
            val series : SeriesResponse? = call.body()

            if(call.isSuccessful) {
                val serieList = series?.series ?: emptyList()
                displaySeries(serieList)
            } else {
                showError()
            }


        }
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun displaySeries(serieList: List<Serie>) {
        TODO("Not yet implemented")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AE2Theme {
        Greeting("Android")
    }
}