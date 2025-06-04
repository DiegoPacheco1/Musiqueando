package com.example.hospitalcompose.ui.pantallas.afinador

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("DefaultLocale")
@Composable
fun AfinadorPantalla(
    vm: AfinadorViewModel = hiltViewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                vm.onEvent(
                    if (uiState.isTuning)
                        AfinadorContract.Event.Stop
                    else
                        AfinadorContract.Event.Start
                )
            }
        ) {
            Text(
                text = if (uiState.isTuning) "Parar Afinador" else "Iniciar Afinador"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isTuning) {
            Text("Nota: ${uiState.note}", fontSize = 28.sp)
            Text(String.format("%.1f Hz", uiState.pitchHz), fontSize = 20.sp)
            Text(String.format("%.1f cents", uiState.cents), fontSize = 18.sp)
        }
    }
}
