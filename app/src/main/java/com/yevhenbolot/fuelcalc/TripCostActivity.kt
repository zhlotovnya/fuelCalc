package com.yevhenbolot.fuelcalc

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TripCostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_cost)

        title = "Вартість поїздки"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val distanceInput = findViewById<EditText>(R.id.distanceTripInput)
        val consumptionInput = findViewById<EditText>(R.id.fuelConsumptionInput)
        val priceInput = findViewById<EditText>(R.id.fuelPriceInput)
        val resultText = findViewById<TextView>(R.id.tripCostResult)
        val calculateBtn = findViewById<Button>(R.id.calculateTripCostButton)
        val backBtn = findViewById<TextView>(R.id.backToMainTrip)

        calculateBtn.setOnClickListener {
            val distance = distanceInput.text.toString().toDoubleOrNull()
            val consumption = consumptionInput.text.toString().toDoubleOrNull()
            val price = priceInput.text.toString().toDoubleOrNull()

            if (distance != null && consumption != null && price != null && distance > 0 && consumption > 0
                && consumption < 60) {
                val cost = (distance / 100) * consumption * price
                resultText.text = getString(R.string.trip_cost_result, cost)
                resultText.visibility = TextView.VISIBLE
            } else {
                resultText.text = getString(R.string.trip_invalid_input)
                resultText.visibility = TextView.VISIBLE
            }
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}