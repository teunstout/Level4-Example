package com.example.remindersapp.database

import androidx.room.*
import com.example.remindersapp.Reminder

@Dao
interface ReminderDOA {
    @Query("SELECT * FROM reminderTable")
    fun getAllReminders() : List<Reminder>

    @Insert
    fun insertReminders(reminder: Reminder)

    @Delete
    fun deleteReminder(reminder: Reminder)

    @Update
    fun updateReminder(reminder: Reminder)
}