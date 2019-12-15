package com.academy.app

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(date)
    }
}