package com.yevhenbolot.fuelcalc
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import java.util.Locale
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val lang = newBase.getSharedPreferences("settings", MODE_PRIVATE)
        val savedLang = lang.getString("lang", null) ?: Locale.getDefault().language

        val newContext = LocaleHelper.setLocale(newBase, savedLang)
        super.attachBaseContext(newContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fuelConsumptionBtn = findViewById<LinearLayout>(R.id.buttonFuelConsumption)
        fuelConsumptionBtn.setOnClickListener {
            startActivity(Intent(this, FuelConsumptionActivity::class.java))
        }

        val tripCostBtn = findViewById<LinearLayout>(R.id.buttonTripCost)
        tripCostBtn.setOnClickListener {
            startActivity(Intent(this, TripCostActivity::class.java))
        }

        val fuelTipsBlock = findViewById<LinearLayout>(R.id.buttonFuelTips)
        fuelTipsBlock.setOnClickListener {
            showFuelTipsDialog()
        }

        val aboutApp = findViewById<TextView>(R.id.aboutApp)
        aboutApp.setOnClickListener {
            showAboutDialog()
        }

        val exit = findViewById<TextView>(R.id.exitApp)
        exit.setOnClickListener {
            finishAffinity()
        }


        val languageText = findViewById<TextView>(R.id.languageText)
        val currentLang = Locale.getDefault().language

        languageText.text = if (currentLang != "en") {
            getString(R.string.language_uk)
        } else {
            getString(R.string.language_en)
        }

        val languageBlock = findViewById<LinearLayout>(R.id.languageBlock)

        languageBlock.setOnClickListener {
            val newLang = if (Locale.getDefault().language != "en") "en" else "uk"

            val locale = Locale(newLang)
            Locale.setDefault(locale)

            val config = resources.configuration
            config.setLocale(locale)
            createConfigurationContext(config)

            getSharedPreferences("settings", MODE_PRIVATE)
                .edit().putString("lang", newLang).apply()

            recreate()
        }
    }


    private fun showFuelTipsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_fuel_tips, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val closeBtn = dialogView.findViewById<Button>(R.id.closeTipsButton)
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
    }


    private fun showAboutDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_about_app, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val closeBtn = dialogView.findViewById<Button>(R.id.closeButton)
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

}