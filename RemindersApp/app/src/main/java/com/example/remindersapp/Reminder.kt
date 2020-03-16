package com.example.remindersapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reminderTable")
data class Reminder(
    @ColumnInfo(name = "reminder")
    val name: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable {
    companion object{
        val STANDERTREMINDERS = arrayOf(
            "Koken",
            "Stofzuigen",
            "Dokters afspraak"
        )
    }
}