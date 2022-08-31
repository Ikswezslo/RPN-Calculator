package com.example.rpn_calculator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.rpn_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator

    private var stackColor: String = "purple"
    private var stackAccuracy: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        calculator = Calculator(binding.result)
        setContentView(view)
        setStackBackgroundColor(stackColor)
        setBtnListeners(calculator)
        binding.stackLayout.setOnTouchListener(object : OnSwipeListener(this) {
            override fun onSwipeRight() {
                calculator.onPressedUndo()
                updateStackUI()
            }
        })
    }

    private fun setBtnListeners(calculator: Calculator) {
        binding.btn0.setOnClickListener { calculator.onPressed0() }
        binding.btn1.setOnClickListener { calculator.onPressedX("1") }
        binding.btn2.setOnClickListener { calculator.onPressedX("2") }
        binding.btn3.setOnClickListener { calculator.onPressedX("3") }
        binding.btn4.setOnClickListener { calculator.onPressedX("4") }
        binding.btn5.setOnClickListener { calculator.onPressedX("5") }
        binding.btn6.setOnClickListener { calculator.onPressedX("6") }
        binding.btn7.setOnClickListener { calculator.onPressedX("7") }
        binding.btn8.setOnClickListener { calculator.onPressedX("8") }
        binding.btn9.setOnClickListener { calculator.onPressedX("9") }
        binding.btnDot.setOnClickListener { calculator.onPressedDot() }
        binding.btnDelete.setOnClickListener { calculator.onPressedDelete() }
        binding.btnEnter.setOnClickListener {
            calculator.onPressedEnter()
            updateStackUI()
        }
        binding.btnSwap.setOnClickListener {
            calculator.onPressedSwap()
            updateStackUI()
        }
        binding.btnDrop.setOnClickListener {
            calculator.onPressedDrop()
            updateStackUI()
        }
        binding.btnClear.setOnClickListener {
            calculator.onPressedClear()
            updateStackUI()
        }
        binding.btnUndo.setOnClickListener {
            calculator.onPressedUndo()
            updateStackUI()
        }
        binding.btnPlus.setOnClickListener {
            calculator.onPressedPlus()
            updateStackUI()
        }
        binding.btnMinus.setOnClickListener {
            calculator.onPressedMinus()
            updateStackUI()
        }
        binding.btnMul.setOnClickListener {
            calculator.onPressedMul()
            updateStackUI()
        }
        binding.btnDiv.setOnClickListener {
            calculator.onPressedDiv()
            updateStackUI()
        }
        binding.btnPow.setOnClickListener {
            calculator.onPressedPow()
            updateStackUI()
        }
        binding.btnSqrt.setOnClickListener {
            calculator.onPressedSqrt()
            updateStackUI()
        }
        binding.btnNeg.setOnClickListener {
            calculator.onPressedNeg()
            updateStackUI()
        }
        binding.btnSettings.setOnClickListener {
            openSettingsActivityForResult()
        }
    }

    private fun updateStackUI() {
        binding.stack1.text = ""
        binding.stack2.text = ""
        binding.stack3.text = ""
        binding.stack4.text = ""

        val stack = calculator.getStack()
        if(stack.size >= 1)
            binding.stack1.text = String.format("%.${stackAccuracy}f", stack[stack.size-1])
        if(stack.size >= 2)
            binding.stack2.text = String.format("%.${stackAccuracy}f", stack[stack.size-2])
        if(stack.size >= 3)
            binding.stack3.text = String.format("%.${stackAccuracy}f", stack[stack.size-3])
        if(stack.size >= 4)
            binding.stack4.text = String.format("%.${stackAccuracy}f", stack[stack.size-4])
    }

    private fun openSettingsActivityForResult() {
        val data = Intent(this, SettingsActivity::class.java)
        data.putExtra("StackColor", stackColor)
        data.putExtra("StackAccuracy", stackAccuracy)
        resultLauncher.launch(data)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                stackColor = data.extras?.getString("StackColor")!!
                stackAccuracy = data.extras?.getInt("StackAccuracy")!!
                setStackBackgroundColor(stackColor)
                updateStackUI()
            }
        }
    }

    private fun setStackBackgroundColor(colorName: String) {
        var color: Int = android.graphics.Color.argb(0,0,0,0)

        when (colorName) {
            "black" -> color = android.graphics.Color.argb(255,30,30,30)
            "red" -> color = android.graphics.Color.argb(255,114,43,34)
            "blue" -> color = android.graphics.Color.argb(255,30,58,110)
            "green" -> color = android.graphics.Color.argb(255,5,73,19)
            "purple" -> color = android.graphics.Color.argb(255,90,48,85)
        }

        binding.stackLayout.setBackgroundColor(color)
    }

}