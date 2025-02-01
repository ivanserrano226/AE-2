package com.example.ae_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ae_2.ui.theme.AE2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AE2Theme {
                SeriesScreen()
            }
        }
    }
}

@Composable
fun SeriesScreen(seriesViewModel: SeriesViewModel = viewModel()) {
    val series by seriesViewModel.series
    LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(series) { serie ->
                SerieCard(serie = serie)
            }
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