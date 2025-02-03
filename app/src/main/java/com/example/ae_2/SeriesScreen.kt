package com.example.ae_2

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun SeriesScreen(
    modifier: Modifier = Modifier,  // Modifier should be first parameter by convention
    seriesViewModel: SeriesViewModel = viewModel()
) {
    val series by seriesViewModel.series
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(series) { serie ->
            SerieCard(serie = serie)
        }
    }
}

@Composable
fun SerieCard(serie: Serie) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.9f)
    ) {
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