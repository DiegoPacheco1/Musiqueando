package com.example.hospitalcompose.data.remote.di

import com.example.compose.BuildConfig
import com.example.hospitalcompose.data.remote.apiServices.*
import com.example.hospitalcompose.data.remote.repositories.ArtistasRepository
import com.example.hospitalcompose.data.remote.repositories.CancionesRepository
import com.example.hospitalcompose.data.remote.repositories.GrabacionesRepository
import com.example.hospitalcompose.data.remote.repositories.SonidosRepository
import com.example.hospitalcompose.data.remote.repositories.UsuariosRepository
import com.example.hospitalcompose.data.utils.AuthInterceptor
import com.example.hospitalcompose.domain.usecases.artistas.*
import com.example.hospitalcompose.domain.usecases.canciones.*
import com.example.hospitalcompose.domain.usecases.grabaciones.*
import com.example.hospitalcompose.domain.usecases.sonidos.*
import com.example.hospitalcompose.domain.usecases.usuarios.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provee Retrofit, OkHttp y todos los ApiServices a nivel de aplicación (singleton).
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Provides @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides @Singleton
    fun provideUsuariosService(retrofit: Retrofit): UsuariosService =
        retrofit.create(UsuariosService::class.java)

    @Provides @Singleton
    fun provideCancionesService(retrofit: Retrofit): CancionesService =
        retrofit.create(CancionesService::class.java)

    @Provides @Singleton
    fun provideArtistasService(retrofit: Retrofit): ArtistasService =
        retrofit.create(ArtistasService::class.java)

    @Provides @Singleton
    fun provideGrabacionesService(retrofit: Retrofit): GrabacionesService =
        retrofit.create(GrabacionesService::class.java)

    @Provides @Singleton
    fun provideSonidosService(retrofit: Retrofit): SonidosService =
        retrofit.create(SonidosService::class.java)
}

/**
 * Provee todos los UseCases a nivel de ViewModel.
 * Nótese que aquí NO hay ningún @Singleton ni binding de servicios.
 */
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    // ─── Usuarios ──────────────────────────────────────────────
    @Provides
    fun provideLoginUsuarioUseCase(repo: UsuariosRepository): LoginUsuarioUseCase =
        LoginUsuarioUseCase(repo)

    @Provides
    fun provideRegistrarUsuarioUseCase(repo: UsuariosRepository): RegistrarUsuarioUseCase =
        RegistrarUsuarioUseCase(repo)

    // ─── Canciones ────────────────────────────────────────────
    @Provides
    fun provideObtenerCancionesUseCase(repo: CancionesRepository): ObtenerCancionesUseCase =
        ObtenerCancionesUseCase(repo)

    @Provides
    fun provideObtenerDetalleCancionUseCase(repo: CancionesRepository): ObtenerDetalleCancionUseCase =
        ObtenerDetalleCancionUseCase(repo)

    @Provides
    fun provideInsertarCancionUseCase(repo: CancionesRepository): InsertarCancionUseCase =
        InsertarCancionUseCase(repo)

    @Provides
    fun provideEliminarCancionUseCase(repo: CancionesRepository): EliminarCancionUseCase =
        EliminarCancionUseCase(repo)

    @Provides
    fun provideBuscarCancionesPorArtistaUseCase(repo: CancionesRepository): BuscarCancionesPorArtistaUseCase =
        BuscarCancionesPorArtistaUseCase(repo)

    // ─── Artistas ────────────────────────────────────────────
    @Provides
    fun provideObtenerArtistasUseCase(repo: ArtistasRepository): ObtenerArtistasUseCase =
        ObtenerArtistasUseCase(repo)

    @Provides
    fun provideObtenerDetalleArtistaUseCase(repo: ArtistasRepository): ObtenerDetalleArtistaUseCase =
        ObtenerDetalleArtistaUseCase(repo)

    @Provides
    fun provideInsertarArtistaUseCase(repo: ArtistasRepository): InsertarArtistaUseCase =
        InsertarArtistaUseCase(repo)

    @Provides
    fun provideActualizarArtistaUseCase(repo: ArtistasRepository): ActualizarArtistaUseCase =
        ActualizarArtistaUseCase(repo)

    @Provides
    fun provideEliminarArtistaUseCase(repo: ArtistasRepository): EliminarArtistaUseCase =
        EliminarArtistaUseCase(repo)

    // ─── Grabaciones ───────────────────────────────────────────
    @Provides
    fun provideObtenerGrabacionesUseCase(repo: GrabacionesRepository): ObtenerGrabacionesUseCase =
        ObtenerGrabacionesUseCase(repo)

    @Provides
    fun provideObtenerDetalleGrabacionUseCase(repo: GrabacionesRepository): ObtenerDetalleGrabacionUseCase =
        ObtenerDetalleGrabacionUseCase(repo)

    @Provides
    fun provideInsertarGrabacionUseCase(repo: GrabacionesRepository): InsertarGrabacionUseCase =
        InsertarGrabacionUseCase(repo)

    @Provides
    fun provideEliminarGrabacionUseCase(repo: GrabacionesRepository): EliminarGrabacionUseCase =
        EliminarGrabacionUseCase(repo)

    // ─── Sonidos ──────────────────────────────────────────────
    @Provides
    fun provideObtenerSonidosUseCase(repo: SonidosRepository): ObtenerSonidosUseCase =
        ObtenerSonidosUseCase(repo)

    @Provides
    fun provideObtenerDetalleSonidoUseCase(repo: SonidosRepository): ObtenerDetalleSonidoUseCase =
        ObtenerDetalleSonidoUseCase(repo)

    @Provides
    fun provideBuscarSonidosPorCategoriaUseCase(repo: SonidosRepository): BuscarSonidosPorCategoriaUseCase =
        BuscarSonidosPorCategoriaUseCase(repo)

    @Provides
    fun provideInsertarSonidoUseCase(repo: SonidosRepository): InsertarSonidoUseCase =
        InsertarSonidoUseCase(repo)
}
