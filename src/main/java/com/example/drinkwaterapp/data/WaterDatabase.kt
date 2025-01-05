package com.example.drinkwaterapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WaterRecord::class], // Jedna entita
    version = 1,
    exportSchema = false
)
abstract class WaterDatabase : RoomDatabase() {
    abstract fun waterDao(): WaterDao
}
