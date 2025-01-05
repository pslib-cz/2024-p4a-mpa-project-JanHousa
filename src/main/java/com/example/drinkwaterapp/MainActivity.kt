package com.example.drinkwaterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.drinkwaterapp.data.WaterDatabase
import com.example.drinkwaterapp.data.WaterRecord
import com.example.drinkwaterapp.ui.theme.DrinkWaterAppTheme
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class MainActivity : ComponentActivity() {

    private lateinit var db: WaterDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializace Room DB
        db = Room.databaseBuilder(
            applicationContext,
            WaterDatabase::class.java,
            "water_database"
        ).build()

        setContent {
            DrinkWaterAppTheme {

                var records by remember { mutableStateOf(emptyList<WaterRecord>()) }
                var totalAmount by remember { mutableStateOf(0) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Vložit do DB
                        Button(onClick = {
                            lifecycleScope.launch {
                                db.waterDao().insertRecord(
                                    WaterRecord(
                                        date = "2025-01-05",
                                        amount = 250
                                    )
                                )
                            }
                        }) {
                            Text(text = "Insert Record")
                        }

                        // Načíst všechny
                        Button(onClick = {
                            lifecycleScope.launch {
                                records = db.waterDao().getAllRecords()
                            }
                        }) {
                            Text(text = "Load Records")
                        }

                        // Načíst součet
                        Button(onClick = {
                            lifecycleScope.launch {
                                val sum = db.waterDao().getTotalAmount() ?: 0
                                totalAmount = sum
                            }
                        }) {
                            Text(text = "Load Total")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Vypsat
                        Text(text = "All records:")
                        records.forEach { record ->
                            Text(text = "Date: ${record.date}, Amount: ${record.amount} ml")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = "Total: $totalAmount ml")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    DrinkWaterAppTheme {
        Text("Preview")
    }
}
