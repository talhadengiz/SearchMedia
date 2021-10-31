package com.talhadengiz.hepsiburada.util

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDateFormat(fromFormat: String, toFormat: String): String {
    val newDate = toDate(fromFormat)
    return newDate?.toFormattedString(toFormat) ?: ""
}

@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String, defaultDate: Date? = null): Date? {
    return try {
        SimpleDateFormat(format).parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        defaultDate
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.toFormattedString(format: String): String {
    return try {
        val date2 = this
        SimpleDateFormat(format, Locale("tr")).format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        "0"
    }
}

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}