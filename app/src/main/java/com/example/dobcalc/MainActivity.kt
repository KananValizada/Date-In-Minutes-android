package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dobcalc.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { view, selectedYear,selectedMonth,selectedDay ->
                var slcdDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                binding.selectedDate.text = slcdDate

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                var theDate = sdf.parse(slcdDate)
                var selectedDateInMinutes = theDate.time / 60000
                var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                var currentDateInMinutes = currentDate.time / 60000
                var differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.ageInMinutes.text = differenceInMinutes.toString()

            },year,month,day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }


}