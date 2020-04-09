package com.example.tipcalculator

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var rbOption1 : RadioButton
    lateinit var rbOption2 : RadioButton
    lateinit var rbOption3 : RadioButton
    lateinit var rbOption4 : RadioButton
    lateinit var rbOption5 : RadioButton
    lateinit var rbOption6 : RadioButton
    lateinit var rbOption7 : RadioButton
    lateinit var rbOption8 : RadioButton

    lateinit var rgTip : RadioGroup
    lateinit var rgPeople: RadioGroup

    lateinit var btnCalculate : Button

    lateinit var tvTip : TextView
    lateinit var tvPeople : TextView
    lateinit var tvPortion : TextView
    lateinit var tvTipTotal : TextView
    lateinit var tvTotal : TextView
    lateinit var etTotal : EditText

    private var customTip : Boolean = false
    private var customPeople : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rbOption1 = findViewById(R.id.rbTip1)
        rbOption2 = findViewById(R.id.rbTip2)
        rbOption3 = findViewById(R.id.rbTip3)
        rbOption4 = findViewById(R.id.rbTipCustom)
        rbOption5 = findViewById(R.id.rbPeople1)
        rbOption6 = findViewById(R.id.rbPeople2)
        rbOption7 = findViewById(R.id.rbPeople3)
        rbOption8 = findViewById(R.id.rbPeopleCustom)
        tvTip = findViewById(R.id.tvTip)
        tvPeople = findViewById(R.id.tvPeople)
        tvPortion = findViewById(R.id.tvPortion)
        etTotal = findViewById(R.id.etTotal)
        tvTipTotal = findViewById(R.id.tvTipTotal)
        tvTotal = findViewById(R.id.tvTotal)
        btnCalculate = findViewById(R.id.btnCalculate)
        rgTip = findViewById(R.id.rgTipGroup)
        rgPeople = findViewById(R.id.rgPeopleGroup)

        // Calculate button
        btnCalculate.setOnClickListener {
            // Error checking

            // Total field is empty
            if (etTotal.text.toString().isEmpty()) {
                missingError()
            } else if (rgTip.checkedRadioButtonId == -1 || rgPeople.checkedRadioButtonId == -1){ // tip/people field has not been chosen
                missingError()
            } else if ((customTip && etTipCustom.text.isEmpty()) || (customPeople && etPeopleCustom.text.isEmpty())) { // Set custom, but empty field
                missingError()
            } else if (etTotal.text.toString().toDouble() < 0.0 || tvTip.text.toString().toInt() < 0 || tvPeople.text.toString().toInt() < 0) { // tip/people field is negative (fail-safe)
                negativeError()
            } else {
                calculate()
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show()
            }
        }

        // Tip radio button
        rbOption1.setOnClickListener {
            tipOption(it)
        }

        // Tip radio button
        rbOption2.setOnClickListener {
            tipOption(it)
        }

        // Tip radio button
        rbOption3.setOnClickListener {
            tipOption(it)
        }

        // Tip radio button
        rbOption4.setOnClickListener {
            tipOption(it)
        }

        // People radio button
        rbOption5.setOnClickListener {
            peopleOption(it)
        }

        // People radio button
        rbOption6.setOnClickListener {
            peopleOption(it)
        }

        // People radio button
        rbOption7.setOnClickListener {
            peopleOption(it)
        }

        // People radio button
        rbOption8.setOnClickListener {
            peopleOption(it)
        }

        // For custom tip percentage
        etTipCustom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tvTip.text = p0
            }

        })

        // For custom number of people
        etPeopleCustom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tvPeople.text = p0
            }

        })

        // Accessibility for clicking out of edit text
        etTotal.setOnFocusChangeListener() { view: View, b: Boolean ->
            if(!b) {

            } else {

            }
        }

        // Accessibility for clicking out of edit text
        etTipCustom.setOnFocusChangeListener() { view: View, b: Boolean ->
            if(!b) {

            } else {

            }
        }

        // Accessibility for clicking out of edit text
        etPeopleCustom.setOnFocusChangeListener() { view: View, b: Boolean ->
            if(!b) {

            } else {

            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun tipOption(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbTip1 ->
                    if (checked) {
                        tvTip.text = rbOption1.text.toString()
                        etTipCustom.isEnabled = false
                        customTip = false
                    }
                R.id.rbTip2 ->
                    if (checked) {
                        tvTip.text = rbOption2.text.toString()
                        etTipCustom.isEnabled = false
                        customTip = false
                    }
                R.id.rbTip3 ->
                    if (checked) {
                        tvTip.text = rbOption3.text.toString()
                        etTipCustom.isEnabled = false
                        customTip = false
                    }
                R.id.rbTipCustom ->
                    if (checked) {
                        etTipCustom.isEnabled = true
                        customTip = true
                    }
            }
        }
    }

    private fun peopleOption(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbPeople1 ->
                    if (checked) {
                        tvPeople.text = rbOption5.text.toString()
                        etPeopleCustom.isEnabled = false
                        customPeople = false
                    }
                R.id.rbPeople2 ->
                    if (checked) {
                        tvPeople.text = rbOption6.text.toString()
                        etPeopleCustom.isEnabled = false
                        customPeople = false
                    }
                R.id.rbPeople3 ->
                    if (checked) {
                        tvPeople.text = rbOption7.text.toString()
                        etPeopleCustom.isEnabled = false
                        customPeople = false
                    }
                R.id.rbPeopleCustom ->
                    if (checked) {
                        etPeopleCustom.isEnabled = true
                        customPeople = true
                    }
            }
        }
    }




    private fun calculate() {
        // Converting Tip to percenetage (15% would 1.15)
        var tip = (tvTip.text.toString().toDouble() / 100) + 1

        var tipPercent = tip.toDouble()

        // Calculating Portions
        var split:Double = etTotal.text.toString().toDouble() * tipPercent  / Integer.parseInt(tvPeople.text.toString())
        var result1:String = "%.2f".format(split)

        // Tip Total
        var tipDecimal : Double = tvTip.text.toString().toDouble() / 100
        var tipAmount = etTotal.text.toString().toDouble() * tipDecimal
        var result2:String = "%.2f".format(tipAmount)

        // Total
        var total = etTotal.text.toString().toDouble() * tipPercent
        var result3:String = "%.2f".format(total)

        tvPortion.text = "$result1"
        tvTotal.text = "$$result2"
        tvTipTotal.text = "$$result3"
    }

    // Generates missing error alert if a field is missing
    private fun missingError() {
        var dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)

        var message = "You must input the required field(s):\n\n"

        if (etTotal.text.toString().isEmpty() || etTotal.text.toString().toDouble() == 0.0) {
            message += "Total\n"
        }

        if (!customTip && tvTip.text.toString() == "0") {
            message += "Tip percentage\n"
        }

        if (customTip && etTipCustom.text.toString().isEmpty()) {
            message += "Custom tip percentage\n"
        }

        if (!customPeople && tvPeople.text.toString() == "0") {
            message += "Number of people\n"
        }

        if (customPeople && etPeopleCustom.text.toString().isEmpty()) {
            message += "Custom number of people"
        }

            dialog.setPositiveButton("OK") { dialog, _ ->
                dialog.cancel()
            }

        dialog.setMessage(message)
        var alert = dialog.create()
        alert.setTitle("Error: Missing Fields")
        alert.show()
    }

    // Generates negative error alert if a field is negative (fail-safe)
    private fun negativeError() {
        var dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)

        var message = "The following field(s) cannot be negative:\n\n"

        if (etTotal.text.toString().toDouble() < 0) {
            message += "Total\n"
        }

        if (tvTip.text.toString().toDouble() < 0) {
            message += "Tip percentage\n"
        }

        if (tvPeople.text.toString().toInt() < 0) {
            message += "Number of people\n"
        }

        if (customTip) {
            message += "Custom tip percentage\n"
        }

        if (customPeople) {
            message += "Custom number of people"
        }

        dialog.setPositiveButton("OK") { dialog, _ ->
            dialog.cancel()
        }

        dialog.setMessage(message)
        var alert = dialog.create()
        alert.setTitle("Error: Negative Fields")
        alert.show()
    }
}
