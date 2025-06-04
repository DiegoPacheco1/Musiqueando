package com.example.hospitalcompose.ui.utils.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.hospitalcompose.ui.pantallas.afinador.AfinadorPantalla
import com.example.hospitalcompose.ui.pantallas.artistas.ArtistasPantalla
import com.example.hospitalcompose.ui.pantallas.canciones.detalle.CancionesDetallePantalla
import com.example.hospitalcompose.ui.pantallas.canciones.listado.CancionesPantalla
import com.example.hospitalcompose.ui.pantallas.grabadora.GrabadoraPantalla
import com.example.hospitalcompose.ui.pantallas.login.LoginPantalla
import com.example.hospitalcompose.ui.pantallas.ritmos.RitmosPantalla
import com.example.hospitalcompose.ui.utils.common.BottomBar
import com.example.hospitalcompose.ui.utils.common.TopBar
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSnackbar = { message: String ->
        scope.launch {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    val state by navController.currentBackStackEntryAsState()
    val currentRoute = state?.destination?.route?.substringBefore("/{")
    val screen = appDestinationList.find { destination ->
        currentRoute == destination.routeIdentifier
    }

    Scaffold(
        topBar = {
            if (screen != LoginDestination) {
                TopBar(navController = navController, screen = screen)
            }
        },
        bottomBar = {
            if (screen != LoginDestination) {
                BottomBar(navController = navController)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Login> {
                LoginPantalla(
                    onNavigateToListaArtistas = {
                        navController.navigate(ListaArtistas)
                    },
                    showSnackbar = { showSnackbar(it) }
                )
            }

            composable<ListaArtistas> {
                ArtistasPantalla(
                    showSnackbar = { showSnackbar(it) },
                    onArtistClick = {
                        navController.navigate(ListaCanciones)
                    },
                )
            }

            composable<Afinador> {
                AfinadorPantalla()
            }

            composable<Ritmos> {
                RitmosPantalla()
            }

            composable<Grabadora> {
              GrabadoraPantalla()
            }

            composable<ListaCanciones> {
                CancionesPantalla(
                    showSnackbar = { showSnackbar(it) },
                    onNavigateToDetalle={ id ->
                        navController.navigate(DetalleCancion(id))
                    },

                    )
            }



            composable<DetalleCancion> { navBackStackEntry ->
                val detalle = navBackStackEntry.toRoute() as DetalleCancion
                CancionesDetallePantalla(
                    showSnackbar = { showSnackbar(it) },
                    cancionId = detalle.id
                )
            }



        }

    }
}
