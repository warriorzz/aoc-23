package com.github.warriorzz.aoc

import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

val days: List<Day> = listOf(Day1(), Day2(), Day3(), Day5(), Day6(), Day7())

fun main(args: Array<String>) {
    val runLastDay = System.getenv("ee.bjarn.runLastDay")
    if (runLastDay != null && runLastDay.toBoolean()) {
        measureTimeMillis {
            println()
            days.last().init()
            days.last().test()
            println()
            days.last().run()
        }.let {
            println("Total time: ${it}ms")
        }
    } else {
        println("Which day would you like to calculate? (or \"all\" or \"exit\" to exit)")
        val line = readlnOrNull()
        when {
            line == "all" -> {
                days.forEach { it.init(); it.run(); println() }
            }

            line?.matches("^\\d{1,2}$".toRegex()) == true -> {
                measureTimeMillis {
                    days[line.toInt() - 1].init()
                    days[line.toInt() - 1].run()
                }.let {
                    println("Total time: ${it}ms")
                }
                println()
            }

            line == "exit" -> exitProcess(0)
            else -> {
                println("Could not determine what you want me todo! >.<")
                println("Please try again!")
                println()
            }
        }
        main(args)
    }
}
