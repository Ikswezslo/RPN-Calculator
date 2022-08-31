package com.example.rpn_calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.rpn_calculator.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val extras = intent.extras ?: return
        val stackColor = extras.getString("StackColor")
        val stackAccuracy = extras.getInt("StackAccuracy")
        if(stackColor != null)
            selectSpinnerItemByValue(binding.spinner, stackColor)
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 8
        binding.numberPicker.wrapSelectorWheel = true
        binding.numberPicker.value = stackAccuracy
        binding.button.setOnClickListener {
            this.finish()
        }
    }

    override fun finish() {
        val data = Intent()
        data.putExtra("StackColor", binding.spinner.selectedItem.toString())
        data.putExtra("StackAccuracy", binding.numberPicker.value)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }

    private fun selectSpinnerItemByValue(spinner: Spinner, value: String) {
        val adapter: SpinnerAdapter = spinner.adapter as SpinnerAdapter
        for (position in 0 until adapter.count) {
            if (adapter.getItem(position) == value) {
                spinner.setSelection(position)
                return
            }
        }
    }
}