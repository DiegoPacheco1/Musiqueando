package com.example.hospitalcompose.data.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.compose.R
import com.example.hospitalcompose.data.local.AppDatabase
import com.example.hospitalcompose.data.local.RitmoDao
import com.example.hospitalcompose.domain.modelo.RitmoEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase {
        val inicial = listOf(
            RitmoEntity(nombre="Palmas",  resId=R.raw.clap),

        )

        // 1) Declaramos la variable antes, sin inicializarla
        lateinit var dbInstance: AppDatabase

        // 2) Creamos el builder, añadimos el callback que capturará dbInstance
        val builder = Room.databaseBuilder(ctx, AppDatabase::class.java, "app_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(dbSql: SupportSQLiteDatabase) {
                    super.onCreate(dbSql)
                    // Ahora sí existe dbInstance en este scope
                    CoroutineScope(Dispatchers.IO).launch {
                        dbInstance.ritmoDao().insertAll(inicial)
                    }
                }
            })
            .fallbackToDestructiveMigration()

        // 3) Por fin construimos la BD y asignamos a dbInstance
        dbInstance = builder.build()
        return dbInstance
    }

    @Provides
    fun provideRitmoDao(db: AppDatabase): RitmoDao = db.ritmoDao()
}

