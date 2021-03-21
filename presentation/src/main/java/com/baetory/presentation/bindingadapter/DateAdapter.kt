package com.baetory.presentation.bindingadapter

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

}