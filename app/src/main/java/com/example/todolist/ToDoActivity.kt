package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.date_todo.*

class ToDoActivity : AppCompatActivity(){
    companion object {
        const val DATE = "DATE"
    }

    private lateinit var toDoAdapter : ToDoMain

    private var currDate = ""

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_todo)
        val text : TextView = findViewById(R.id.date)
        currDate = intent.getStringExtra(DATE).toString()
        text.text = currDate
        toDoAdapter = ToDoMain(mutableListOf())
        todoItems.adapter = toDoAdapter
        todoItems.layoutManager = LinearLayoutManager(this)
        insertItem.setOnClickListener {
            val alert : AlertDialog.Builder = AlertDialog.Builder(this)
            alert.setTitle("Insert To-Do Item")
            val input = EditText(this)
            input.hint = "Type here..."
            input.inputType = InputType.TYPE_CLASS_TEXT
            alert.setView(input)
            alert.setPositiveButton("Okay") { _, _ ->
                val itemText = input.text.toString()
                if (itemText.isNotEmpty()) {
                    val item = ToDoItem(itemText)
                    toDoAdapter.addItem(item)
                }
            }
            alert.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            alert.show()
        }
        goBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}