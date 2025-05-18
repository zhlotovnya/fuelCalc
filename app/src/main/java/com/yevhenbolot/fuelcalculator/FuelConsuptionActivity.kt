package com.yevhenbolot.fuelcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Класс для обчислення росходу палива на 100 км
class FuelConsumptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_consumption)

        title = "Витрата палива"

        val distanceInput = findViewById<EditText>(R.id.distanceInput)
        val fuelUsedInput = findViewById<EditText>(R.id.fuelUsedInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val calculateBtn = findViewById<Button>(R.id.calculateButton)

        calculateBtn.setOnClickListener {
            val distance = distanceInput.text.toString().toDoubleOrNull()
            val fuel = fuelUsedInput.text.toString().toDoubleOrNull()

            if (distance != null && fuel != null && distance > 0) {
                val consumption = (fuel / distance) * 100
                val result = getString(R.string.result_format, consumption)
                resultText.text = result
                resultText.visibility = TextView.VISIBLE
            } else {
                resultText.text = getString(R.string.invalid_input)
                resultText.visibility = TextView.VISIBLE
            }
        }

        val backText = findViewById<TextView>(R.id.backToMain)
        backText.setOnClickListener {
            finish()
        }
    }
}