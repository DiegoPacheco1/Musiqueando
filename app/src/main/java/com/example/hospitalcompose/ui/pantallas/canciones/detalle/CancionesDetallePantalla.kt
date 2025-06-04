// CancionesDetallePantalla.kt
package com.example.hospitalcompose.ui.pantallas.canciones.detalle

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
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
fun CancionesDetallePantalla(
    cancionId: String,
    viewModel: CancionesDetalleViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit
) {
    val ui by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(cancionId) {
        viewModel.handleEvent(CancionesDetalleContract.DetalleEvent.LoadDetalle(cancionId))
    }
    LaunchedEffect(ui.uiEvent) {
        ui.uiEvent?.let { if (it is UiEvent.ShowSnackbar) showSnackbar(it.message) }
        viewModel.handleEvent(CancionesDetalleContract.DetalleEvent.UiEventDone)
    }

    Box(Modifier.fillMaxSize().padding(16.dp)) {
        when {
            ui.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            ui.cancion != null -> {
                val can     = ui.cancion!!
                val acordes = ui.shiftedAcordes
                val cejilla = can.cejilla?.toString() ?: "0"
                val lineas  = can.letra.orEmpty()

                Column(Modifier.fillMaxSize()) {
                    // 1) Título
                    Text(can.nombre ?: "", style = MaterialTheme.typography.headlineSmall)

                    Spacer(Modifier.height(12.dp))

                    // 2) Acordes con botones a los lados
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { viewModel.handleEvent(CancionesDetalleContract.DetalleEvent.TransposeDown) }
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Bajar semitono")
                        }

                        Row(
                            Modifier
                                .weight(1f)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            acordes.forEach { chord ->
                                Text(
                                    text = chord,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                            }
                        }

                        IconButton(
                            onClick = { viewModel.handleEvent(CancionesDetalleContract.DetalleEvent.TransposeUp) }
                        ) {
                            Icon(Icons.Default.ArrowForward, contentDescription = "Subir semitono")
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    // 3) Cejilla
                    Text("Cejilla: $cejilla", style = MaterialTheme.typography.bodyLarge)

                    Spacer(Modifier.height(8.dp))
                    Divider()
                    Spacer(Modifier.height(8.dp))

                    // 4) Letra
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(lineas) { linea ->
                            Text(text = linea, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            else -> Text(
                text = "No se encontró la canción",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
