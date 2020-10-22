package com.doodleblue.project.utils

import java.math.BigDecimal
import java.text.Format
import java.text.NumberFormat
import java.util.*

class Convert {
    companion object {
        fun StrToMoney(values: String): String {
            val format: Format =
                NumberFormat.getCurrencyInstance(Locale("en", "in"))

            return format.format(BigDecimal(values))
        }


    }
}