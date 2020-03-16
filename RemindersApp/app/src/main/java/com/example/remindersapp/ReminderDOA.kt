package com.example.remindersapp

import androidx.room.*

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