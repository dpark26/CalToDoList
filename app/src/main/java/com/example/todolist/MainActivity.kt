package com.example.todolist

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start : Button = findViewById(R.id.start)
        val button : Button = findViewById(R.id.toDoButton)
        start.setOnClickListener{
            chooseDate(button)
        }
        button.setOnClickListener {
            startActivity(Intent(this, ToDoActivity::class.java).putExtra(ToDoActivity.DATE, button.text.toString()))
        }
    }

    private fun chooseDate(button : Button) {
        val cal = Calendar.getInstance()
        val currYear = cal.get(Calendar.YEAR)
        val currMonth = cal.get(Calendar.MONTH)
        val currDay = cal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this, {_, year, month, day ->
                if (year < currYear) {
                    Toast.makeText(this, "That date has already passed!", Toast.LENGTH_LONG).show()
                }
                else if (year == currYear) {
                    if (month < currMonth) {
                        Toast.makeText(this, "That date has already passed!", Toast.LENGTH_LONG)
                            .show()
                    }
                    else if (month == currMonth && day < currDay) {
                        Toast.makeText(this, "That date has already passed!", Toast.LENGTH_LONG).show()
                    }
                    else {
                        val str = "${month+1}/${day}/${year}"
                        button.text = str
                        button.isVisible = true
                    }
                }
                else {
                    val str = "${month+1}/${day}/${year}"
                    button.text = str
                    button.isVisible = true
                }
            }, currYear, currMonth, currDay
        ).show()
    }
}