package com.example.remindersapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(val name: String) : Parcelable {
    companion object{
        val STANDERTREMINDERS = arrayOf(
            "Koken",
            "Stofzuigen",
            "Dokters afspraak"
        )
    }
}