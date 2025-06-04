package com.example.hospitalcompose.ui.pantallas.artistas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hospitalcompose.ui.utils.common.UiEvent


@Composable
fun ArtistasPantalla(
    viewModel: ArtistasViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    onArtistClick: (String) -> Unit
) {
    val ui by viewModel.ui.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(ArtistasContract.ArtistasEvent.LoadArtistas)
    }
    LaunchedEffect(ui.uiEvent) {
        ui.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) showSnackbar(it.message)
            viewModel.handleEvent(ArtistasContract.ArtistasEvent.UiEventDone)
        }
    }

    if (ui.isLoading) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(ui.artistas.orEmpty()) { artista ->
                ArtistaItem(
                    nombre = artista.nombre,
                    onClick = { onArtistClick(artista.id) }
                )
            }
        }
    }
}

@Composable
fun ArtistaItem(
    nombre: String,
    onClick: () -> Unit
) {
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Ver detalles",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
