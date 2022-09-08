package com.sample.turtlemint


import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    //Use this standard format
    const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    const val DATE_FORMAT = "MM-dd-yyyy"

    fun getDate(date: String): String {

        return try {
            val inputDF =
                SimpleDateFormat(ISO_8601, Locale.getDefault())
            val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            sdf.format(inputDF.parse(date))
        } catch (e: Exception) {
            Log.e("Date Parse Exception", "" + e.toString())
            e.toString()
        }
    }
}