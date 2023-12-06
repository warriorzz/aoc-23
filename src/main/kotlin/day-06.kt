package com.github.warriorzz.aoc

class Day6 : Day(6) {
    override fun init() {

    }

    override val partOne: (List<String>, Boolean) -> String = { input, _ ->
        val times = input[0].split(":")[1].replace("\\W+".toRegex(), " ").trim().split(" ").map { it.toInt() }
        val distances = input[1].split(":")[1].replace("\\W+".toRegex(), " ").trim().split(" ").map { it.toInt() }
        var count = 1
        for (i in times.indices) {
            val wins = (0..times[i]).map {
                it * (times[i] - it)
            }.count { it > distances[i] }
            count *= wins
        }

        count.toString()
    }

    override val partTwo: (List<String>, Boolean) -> String = { input, _ ->
        val time = input[0].split(":")[1].replace(" ", "").toLong()
        val distance = input[1].split(":")[1].replace(" ", "").toLong()

        (0..time).map {
            it * (time - it)
        }.count { it > distance }.toString()
    }
}
