package com.example.drinkwaterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WaterRecord")
data class WaterRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val amount: Int
)
