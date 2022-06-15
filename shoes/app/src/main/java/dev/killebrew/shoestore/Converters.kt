package dev.killebrew.shoestore

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

/**
 * Handle converting shoe size to and from a string for EditText to display it.
 */
object Converters {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(
        value: Double
    ): String {
        return if (value > 0) value.toString() else ""
    }

    @JvmStatic
    fun stringToDouble(
        value: String
    ): Double {
        return try {
            value.toDouble()
        } catch (ex: NumberFormatException) {
            0.0
        }
    }
}