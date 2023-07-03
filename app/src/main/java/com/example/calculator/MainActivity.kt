package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calcutateTip() }

    }
    fun calcutateTip(){

        val stringInTextField=binding.costOfService.text.toString()
        val cost=stringInTextField.toDouble()
        val select=binding.tipOptions.checkedRadioButtonId;
        try {
            val tipPrecentage = when (select) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eightenn_percent -> 0.18
                else -> 0.15
            }
            var tip = cost * tipPrecentage
            val roundUp = binding.roundUpSwitch.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)

            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }catch (e: NumberFormatException){
            Toast.makeText(getApplicationContext(), "Introduceți costul serviciului!", Toast.LENGTH_SHORT).show()
        }
    }
}
