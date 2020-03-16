package com.example.remindersapp.database

import android.content.Context
import com.example.remindersapp.Reminder

class ReminderRepository(context: Context) {

    private lateinit var reminderDao: ReminderDOA

    init {
        val reminderRoomDatabase = ReminderRoomDatabase.getDatabase(context)
        if (reminderRoomDatabase != null) {
            reminderDao = reminderRoomDatabase.reminderDao()

            fun getAllReminders(): List<Reminder> {
                return reminderDao.getAllReminders()
            }

            fun insertReminder(reminder: Reminder) {
                reminderDao.insertReminder(reminder)
            }

            fun deleteReminder(reminder: Reminder) {
                reminderDao.deleteReminder(reminder)
            }

            fun updateReminder(reminder: Reminder) {
                reminderDao.updateReminder(reminder)
            }
        }
    }
}