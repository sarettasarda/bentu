package ui

import kotlinx.datetime.LocalDate

fun Double.asMoney(): String {
    val rounded = (this * 100).toInt()
    val euros = rounded / 100
    val cents = rounded % 100
    return "$euros.${cents.toString().padStart(2, '0')}"
}

fun formatIsoToItalian(dateIso: String): String {
    return try {
        val date = LocalDate.parse(dateIso) // Parses 2026-01-10
        val day = date.dayOfMonth
        val year = date.year
        val month = when(date.monthNumber) {
            1 -> "gennaio"
            2 -> "febbraio"
            3 -> "marzo"
            4 -> "aprile"
            5 -> "maggio"
            6 -> "giugno"
            7 -> "luglio"
            8 -> "agosto"
            9 -> "settembre"
            10 -> "ottobre"
            11 -> "novembre"
            12 -> "dicembre"
            else -> ""
        }
        "$day $month $year"
    } catch (e: Exception) {
        dateIso
    }
}