package com.example.hospitalcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hospitalcompose.data.local.converters.RoomConverters
import com.example.hospitalcompose.domain.modelo.RitmoEntity


@Database(entities = [ RitmoEntity::class ], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ritmoDao(): RitmoDao
}
