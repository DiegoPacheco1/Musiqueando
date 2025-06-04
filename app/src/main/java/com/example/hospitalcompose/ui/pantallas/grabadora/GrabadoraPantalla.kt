package com.example.hospitalcompose.ui.pantallas.grabadora

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import java.io.File

@Composable
fun GrabadoraPantalla(
    viewModel: GrabadoraViewModel = hiltViewModel()
) {
    val ui by viewModel.ui.collectAsState()
    val context = LocalContext.current

    // Launcher para petición de permiso RECORD_AUDIO
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.handle(GrabadoraContract.Event.StartRecording)
        } else {
            Toast.makeText(context, "Necesito permiso de micrófono", Toast.LENGTH_SHORT).show()
        }
    }

    Column(Modifier.fillMaxSize()) {
        // Zona superior: botón Grabar / Detener + cronómetro
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            if (!ui.isRecording) {
                Button(onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.RECORD_AUDIO
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        viewModel.handle(GrabadoraContract.Event.StartRecording)
                    } else {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                }) {
                    Text("Grabar")
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Grabando… ${ui.timerSec}s")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = {
                        viewModel.handle(GrabadoraContract.Event.StopRecording)
                    }) {
                        Text("Detener")
                    }
                }
            }
        }

        Divider()

        // Zona inferior: lista de grabaciones con borde
        val shape = MaterialTheme.shapes.small
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    shape = shape
                )
                .clip(shape)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(ui.recordings) { file ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(file.nameWithoutExtension, style = MaterialTheme.typography.bodyLarge)
                        IconButton(onClick = {
                            viewModel.handle(GrabadoraContract.Event.Play(file))
                        }) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "Play")
                        }
                    }
                    Divider()
                }
            }
        }
    }
}
