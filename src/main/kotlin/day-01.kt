package com.github.warriorzz.aoc

class Day1 : Day(1) {

    override val partOne: (List<String>, Boolean) -> String = { input, _ ->
        var total = 0
        for (line in input) {
            val digits = line.filter { it.isDigit() }.map { it.digitToInt() }
            total += (digits.first()) * 10 + (digits.last())
        }
        total.toString()
    }

    override val partTwo: (List<String>, Boolean) -> String = { input, _ ->
        var total2 = 0
        for (line in input) {
            val newLine = line.replace("one", "one1one").replace("two", "two2two").replace("three", "three3three")
                .replace("four", "four4four").replace("five", "five5five").replace("six", "six6six").replace("seven", "seven7seven")
                .replace("eight", "eight8eight").replace("nine", "nine9nine")
            val digits = newLine.filter { it.isDigit() }.map { it.digitToInt() }
            total2 += (digits.first()) * 10 + (digits.last())
        }
        total2.toString()
    }

    override fun init() {}
}
