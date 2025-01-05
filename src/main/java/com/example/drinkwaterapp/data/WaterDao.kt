package com.example.drinkwaterapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterDao {
    @Insert
    suspend fun insertRecord(record: WaterRecord)

    @Query("SELECT * FROM WaterRecord")
    suspend fun getAllRecords(): List<WaterRecord> // Ujistěte se, že `WaterRecord` je anotováno jako @Entity

    @Query("SELECT SUM(amount) FROM WaterRecord")
    suspend fun getTotalAmount(): Int?
}
