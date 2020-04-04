package com.example.tipcalculator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var tip : Spinner
    lateinit var people : Spinner

    lateinit var tvTip : TextView
    lateinit var tvPeople : TextView
    lateinit var btnCalculate : Button
    lateinit var tvPortion : TextView
    lateinit var etTotal : EditText
    lateinit var tvTipTotal : TextView
    lateinit var tvTotal : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tip = findViewById<Spinner>(R.id.sTipOption)
        people = findViewById<Spinner>(R.id.sPeopleCount)

        tvTip = findViewById<TextView>(R.id.tvTip)
        tvPeople = findViewById<TextView>(R.id.tvPeople)
        tvPortion = findViewById<TextView>(R.id.tvPortion)
        etTotal = findViewById<EditText>(R.id.etTotal)
        tvTipTotal = findViewById<TextView>(R.id.tvTipTotal)
        tvTotal = findViewById<TextView>(R.id.tvTotal)

        btnCalculate = findViewById<Button>(R.id.btnCalculate)

        val tipOptions = arrayOf("10", "15" , "18")
        val peopleOptions = arrayOf("1", "2", "3", "4", "5")

        tip.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, tipOptions)
        people.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, peopleOptions)


        // Tip methods
        tip.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
                tvTip.text = "0%"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvTip.text = "${tipOptions[p2]}%"
            }
        }

        // People methods
        people.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                tvPeople.text = "0"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvPeople.text = "${peopleOptions[p2]}"
            }

        }

        // Calculate button
        btnCalculate.setOnClickListener {
            calculate()
        }

    }

    private fun calculate() {
        // Converting Tip to percenetage (15% would 1.15)
        var tip = "1.${tvTip.text}"
        tip = tip.substring(0, tip.length - 1)
        var tipPercent = tip.toDouble()

        // Calculating Portions
        var split:Double = etTotal.text.toString().toDouble() * tipPercent  / Integer.parseInt(tvPeople.text.toString())
        var result1:String = "%.2f".format(split)

        // Tip Total
        var tipDecimal = ".${tvTip.text}"
        tipDecimal = tipDecimal.substring(0, tipDecimal.length - 1)
        var tipAmount = etTotal.text.toString().toDouble() * tipDecimal.toDouble()
        var result2:String = "%.2f".format(tipAmount)

        // Total
        var total = etTotal.text.toString().toDouble() * tipPercent
        var result3:String = "%.2f".format(total)

        tvPortion.text = "$result1"
        tvTipTotal.text = "$$result2"
        tvTotal.text = "$$result3"
    }
}
