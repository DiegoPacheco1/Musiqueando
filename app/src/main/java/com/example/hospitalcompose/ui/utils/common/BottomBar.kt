package com.example.hospitalcompose.ui.utils.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.compose.R
import com.example.hospitalcompose.common.Constantes
import com.example.hospitalcompose.ui.utils.navigation.Afinador
import com.example.hospitalcompose.ui.utils.navigation.Grabadora
import com.example.hospitalcompose.ui.utils.navigation.ListaArtistas
import com.example.hospitalcompose.ui.utils.navigation.Ritmos


@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_music_note_24),
                    contentDescription = Constantes.CANCIONES
                )
            },
            label = { Text(Constantes.CANCIONES) },
            selected = false,
            onClick = { navController.navigate(ListaArtistas) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cell_tower_24),
                    contentDescription = Constantes.CANCIONES
                )
            },
            label = { Text(Constantes.AFINADOR) },
            selected = false,
            onClick = { navController.navigate(Afinador) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_mic_24),
                    contentDescription = Constantes.CANCIONES
                )
            },
            label = { Text(Constantes.GRABADORA) },
            selected = false,
            onClick = { navController.navigate(Grabadora) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_hearing_24),
                    contentDescription = Constantes.CANCIONES
                )
            },
            label = { Text(Constantes.RITMOS) },
            selected = false,
            onClick = { navController.navigate(Ritmos) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Burritillos"
                )
            },
            label = { Text("Burritillos papa") },
            selected = false,
            onClick = { navController.navigate(Ritmos) }
        )



    }
}
