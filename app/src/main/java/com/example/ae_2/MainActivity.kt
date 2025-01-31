package com.example.ae_2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            runOnUiThread{
                if(call.isSuccessful) {
                    val serieList = series?.series ?: emptyList()
                    displaySeries(serieList)
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error al cargar series", Toast.LENGTH_SHORT).show()
    }

    private fun displaySeries(serieList: List<Serie>) {
        TODO("Not yet implemented")
    }
}

@Composable
fun SerieCard(serie: Serie) {
    Card(modifier = Modifier.padding(10.dp).fillMaxWidth(0.9f)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = serie.image,
                contentDescription = "${serie.title} cover",
                modifier = Modifier.size(120.dp)
            )

            Text(serie.title)
            Text("★ ${serie.rating}", color = Color.Yellow)
            Text(serie.channel, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun SerieCardPreview() {
    MaterialTheme {
        SerieCard(
            serie = Serie(
                id = 1,
                title = "The Great Series",
                creator = "John Doe",
                rating = 8.9,
                dates = "2020-2023",
                image = "",
                channel = "StreamTV"
            )
        )
    }
}