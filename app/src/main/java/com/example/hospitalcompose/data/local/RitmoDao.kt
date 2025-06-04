package com.example.hospitalcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hospitalcompose.domain.modelo.RitmoEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface RitmoDao {
    @Query("SELECT * FROM ritmos ORDER BY nombre")
    fun getAll(): Flow<List<RitmoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RitmoEntity>)
}

