package com.example.hospitalcompose.ui.pantallas.ritmos

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hospitalcompose.domain.usecases.rtimos.RitmoViewModel

@Composable
fun RitmosPantalla(viewModel: RitmoViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    val list by viewModel.ritmos.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { ritmo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(ritmo.nombre, Modifier.weight(1f))
                IconButton(onClick = {
                    MediaPlayer.create(ctx, ritmo.resId).apply {
                        setOnCompletionListener { release() }
                        start()
                    }
                }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Reproducir ${ritmo.nombre}")
                }
            }
            Divider()
        }
    }
}
