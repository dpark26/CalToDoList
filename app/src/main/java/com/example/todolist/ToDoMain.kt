package com.example.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoMain(private val items: MutableList<ToDoItem>) : RecyclerView.Adapter<ToDoMain.ToDoListView>(){

    class ToDoListView(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListView {
        return ToDoListView(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))
    }

    private fun toggleHighlight(toDoName: TextView, done: Boolean) {
        if (done) {
            toDoName.setBackgroundColor(Color.YELLOW)
        }
        else {
            toDoName.setBackgroundColor(Color.WHITE)
        }
    }

    override fun onBindViewHolder(holder: ToDoListView, position: Int) {
        val curr = items[position]
        holder.itemView.apply {
            toDoName.text = curr.name
            toDoDone.isChecked = curr.done
            toggleHighlight(toDoName, curr.done)
            toDoDone.setOnCheckedChangeListener{
                _, done ->
                toggleHighlight(toDoName, done)
                curr.done = !curr.done
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(todo: ToDoItem) {
        items.add(todo)
        notifyItemInserted(items.size-1)
    }
}