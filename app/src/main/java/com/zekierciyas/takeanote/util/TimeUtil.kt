package com.zekierciyas.takeanote.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    /**
     * returns current time as Long.
     */
    fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    /** returns simple date format as yyyy/MM/dd.
     * @param timeInMs input time as Long
     */
    @SuppressLint("SimpleDateFormat")
    fun getDateFormat(timeInMs: Long): String {
        val date = Date(timeInMs)
        val simpleDateFormat = SimpleDateFormat("HH:mm yyyy/MM/dd")
        return simpleDateFormat.format(date)
    }


}