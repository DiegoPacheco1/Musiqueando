package com.example.hospitalcompose.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String): List<String> =
        gson.fromJson(value, object: TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun toStringList(list: List<String>): String =
        gson.toJson(list)

    @TypeConverter
    fun fromIntList(value: String): List<Int> =
        gson.fromJson(value, object: TypeToken<List<Int>>() {}.type)

    @TypeConverter
    fun toIntList(list: List<Int>): String =
        gson.toJson(list)
}
