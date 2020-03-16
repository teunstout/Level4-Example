package com.example.remindersapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.remindersapp.database.ReminderRepository

import kotlinx.android.synthetic.main.activity_main.*

val ADD_REMINDER_REQUEST_CODE = 100;

class MainActivity : AppCompatActivity() {
    private var reminders = arrayListOf<Reminder>()
    private var reminderAdapter = ReminderAdapter(reminders)
    private lateinit var reminderRepository: ReminderRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startAddActivity()
        }

        reminderRepository = ReminderRepository(this)

        initViews()
    }

    private fun initViews() {


        rvReminder.layoutManager = StaggeredGridLayoutManager(1, 1)
        rvReminder.adapter = reminderAdapter
        rvReminder.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvReminder)
        getRemindersFromDatabase()


    }

    private fun getRemindersFromDatabase() {
        val reminders = reminderRepository.getAllReminders()
        this@MainActivity.reminders.clear()
        this@MainActivity.reminders.addAll(reminders)
        reminderAdapter.notifyDataSetChanged()
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_REMINDER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_REMINDER_REQUEST_CODE -> {
                    // kan beter zonder !!
                    val reminder = data!!.getParcelableExtra<Reminder>(EXTRA_REMINDER)
                    reminderRepository.insertReminder(reminder)
                    getRemindersFromDatabase()
                }
            }
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reminderToDelete = reminders[position]

                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()

                reminderRepository.deleteReminder(reminderToDelete)


                getRemindersFromDatabase()
            }
        }
        return ItemTouchHelper(callback)
    }

}
