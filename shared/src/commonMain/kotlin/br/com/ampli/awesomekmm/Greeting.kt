package br.com.ampli.awesomekmm

import kotlinx.datetime.*

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}! there are only ${daysUntilNewYear()} days until new year"
    }

    fun daysUntilNewYear(): Int {
        val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
        val closestNewYear = LocalDate(today.year + 1, 1, 1)
        return today.daysUntil(closestNewYear)
    }

}

