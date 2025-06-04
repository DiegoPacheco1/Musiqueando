package com.example.hospitalcompose.ui.utils.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.reflect.KClass

val appDestinationList = listOf(
    LoginDestination,
    ListaCancionesDestination,
    ListaArtistasDestination,
    DetalleCancionDestination,
    RitmosDestination,
    GrabadoraDestination
)

interface AppDestination {
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState()

    val routeIdentifier: String
        get() = when (route) {
            is KClass<*> -> (route as KClass<*>).qualifiedName ?: ""
            else -> route::class.qualifiedName ?: ""
        }
}

interface AppMainBottomDestination : AppDestination {
    val onBottomBar: Boolean
    val icon: ImageVector
}


object LoginDestination : AppDestination {
    override val route = Login
    override val title = "Login"
}

object ListaCancionesDestination : AppMainBottomDestination {
    override val route = ListaCanciones
    override val title = "Canciones"
    override val onBottomBar = true
    override val icon = Icons.Default.Person
}



object ListaArtistasDestination : AppMainBottomDestination {
    override val route       = ListaArtistas
    override val title       = "Artistas"
    override val onBottomBar = true
    override val icon        = Icons.Filled.Person
}


object AfinadorDestination : AppMainBottomDestination {
    override val route       = Afinador
    override val title       = "Afinador"
    override val onBottomBar = true
    override val icon        = Icons.Filled.Person
}

object GrabadoraDestination : AppMainBottomDestination {
    override val route       = Afinador
    override val title       = "Grabadora"
    override val onBottomBar = true
    override val icon        = Icons.Filled.Person
}

object RitmosDestination : AppMainBottomDestination {
    override val route       = Ritmos
    override val title       = "Ritmos"
    override val onBottomBar = true
    override val icon        = Icons.Filled.Person
}

object DetalleCancionDestination : AppDestination {
    override val route = DetalleCancion
    override val title = "DetallePelicula"
}







