package com.example.remindersapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.reminder_template.view.*

class ReminderAdapter(val standardTasks: ArrayList<Reminder>) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    inner class ViewHolder(textview: View) : RecyclerView.ViewHolder(textview) {
        fun bind(reminder: Reminder) {
            itemView.reminderName.text = reminder.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reminder_template, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return standardTasks.size
    }

    override fun onBindViewHolder(holder: ReminderAdapter.ViewHolder, position: Int) {
        holder.bind(standardTasks[position])
    }

}