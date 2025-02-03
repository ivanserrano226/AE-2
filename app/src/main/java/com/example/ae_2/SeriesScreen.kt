package com.example.ae_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun SeriesScreen(
    modifier: Modifier = Modifier,
    seriesViewModel: SeriesViewModel = viewModel()
) {
    val series by seriesViewModel.series
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
            .fillMaxWidth(0.8f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = serie.image,
                contentDescription = "${serie.title} cover",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                serie.title,
                textAlign = TextAlign.Center
            )
            Text(
                "★ ${serie.rating}",
                color = Color.Yellow,
                textAlign = TextAlign.Center
            )
            Text(
                serie.channel,
                textAlign = TextAlign.Center
            )
        }
    }
}