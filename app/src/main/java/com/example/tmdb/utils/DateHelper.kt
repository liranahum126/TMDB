package com.example.tmdb.utils

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {
        private const val SERVER_DATE_FORMAT = "yyyy-MM-dd"

        fun toServerDateFormat(date: Date): String {
            val simpleDateFormat = SimpleDateFormat(SERVER_DATE_FORMAT)
            return simpleDateFormat.format(date)
        }
    }
}