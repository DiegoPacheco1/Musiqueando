package com.example.hospitalcompose.domain.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ritmos")
data class RitmoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val resId: Int
)
