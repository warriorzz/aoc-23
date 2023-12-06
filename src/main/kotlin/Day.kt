package com.github.warriorzz.aoc

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.system.measureTimeMillis

abstract class Day(day: Int) {

    private val dayString = day
    protected val input = Path("./input/day-$day.txt").readLines()
    protected val testInput = Path("./input/day-$day-test.txt").readLines()

    abstract val partOne: (List<String>, Boolean) -> String
    abstract val partTwo: (List<String>, Boolean) -> String

    fun run() {
        println("Day $dayString:")
        val partOneTime = measureTimeMillis { println("Solution of part 1: ${partOne.invoke(input, false)}") }
        val partTwoTime = measureTimeMillis { println("Solution of part 2: ${partTwo.invoke(input, false)}") }
        println("Took ${partOneTime}ms for part 1 and ${partTwoTime}ms for part 2.")
    }

    fun test() {
        println("Testing Day $dayString:")
        val partOneTime = measureTimeMillis { println("Test of part 1: ${partOne.invoke(testInput, true)}") }
        val partTwoTime = measureTimeMillis { println("Test of part 2: ${partTwo.invoke(testInput, true)}") }
        println("Took ${partOneTime}ms for testing part 1 and ${partTwoTime}ms for testing part 2.")
    }

    abstract fun init()
}
