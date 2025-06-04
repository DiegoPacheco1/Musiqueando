// BurritosPantalla.kt
package com.example.hospitalcompose.ui.pantallas.canciones.listado

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.hospitalcompose.domain.modelo.Cancion
import com.example.hospitalcompose.ui.utils.common.UiEvent

@Composable
fun CancionesPantalla(
    viewModel: CancionesViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    onNavigateToDetalle: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(CancionesContract.CancionesEvent.LoadCanciones)
    }
    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let { event ->
            if (event is UiEvent.ShowSnackbar) showSnackbar(event.message)
            viewModel.handleEvent(CancionesContract.CancionesEvent.UiEventDone)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            ListaDeCanciones(
                lista = uiState.canciones.orEmpty(),
                onClick = onNavigateToDetalle
            )
        }
    }
}

@Composable
fun ListaDeCanciones(
    lista: List<Cancion>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(lista) { cancion ->
            CancionItem(
                cancion = cancion,
                onClick = { onClick(cancion.id) }
            )
        }
    }
}

@Composable
fun CancionItem(
    cancion: Cancion,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Nombre (por si viene null o vacío)
                val nombreText = cancion.nombre.takeIf { !it.isNullOrBlank() } ?: "Título desconocido"
                Text(
                    text = nombreText,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Artista (por si viene null o vacío)
                val artistaText = cancion.artista.takeIf { !it.isNullOrBlank() } ?: "Artista desconocido"
                Text(
                    text = artistaText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Ver detalles",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
