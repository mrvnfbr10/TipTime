package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener { calculateTip() }
    }
    private fun calculateTip(){
        val costField = binding.costFieldEdit.text.toString()
        val cost = costField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val tipId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(tipId){
            R.id.twentyPercent -> 0.20
            R.id.eighteenPercent -> 0.18
            R.id.fifteenPercent -> 0.15
            else -> 0.00
        }
        var tip = tipPercentage * cost
        if(binding.roundTip.isChecked){
            tip = ceil(tip)
        }
        val finalTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, finalTip)
    }
}