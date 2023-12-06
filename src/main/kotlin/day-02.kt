package com.github.warriorzz.aoc

class Day2 : Day(2) {
    var games: List<Game> = listOf()
    var testGames: List<Game> = listOf()

    override fun init() {
        games = input.map { line ->
            val id = line.split(": ")[0].split(" ")[1].toInt()
            val sets = line.split(": ")[1].split("; ").map { set ->
                var green = 0
                var blue = 0
                var red = 0
                set.split(", ").forEach {
                    when (it.split(" ")[1]) {
                        "green" -> green = it.split(" ")[0].toInt()
                        "red" -> red = it.split(" ")[0].toInt()
                        "blue" -> blue = it.split(" ")[0].toInt()
                    }
                }
                CubeSet(blue, green, red)
            }
            Game(id, sets)
        }
        testGames = testInput.map { line ->
            val id = line.split(": ")[0].split(" ")[1].toInt()
            val sets = line.split(": ")[1].split("; ").map { set ->
                var green = 0
                var blue = 0
                var red = 0
                set.split(", ").forEach {
                    when (it.split(" ")[1]) {
                        "green" -> green = it.split(" ")[0].toInt()
                        "red" -> red = it.split(" ")[0].toInt()
                        "blue" -> blue = it.split(" ")[0].toInt()
                    }
                }
                CubeSet(blue, green, red)
            }
            Game(id, sets)
        }
    }

    override val partOne: (List<String>, Boolean) -> String = { _, testing ->
        (if (testing) testGames else games).filter { game -> game.sets.all { it.green <= 13 && it.blue <= 14 && it.red <= 12 } }.map { it.id }.reduce { acc, i -> acc + i }.toString()
    }

    override val partTwo: (List<String>, Boolean) -> String = { _, testing ->
        (if (testing) testGames else games).sumOf {
            var minRed = 0
            var minGreen = 0
            var minBlue = 0
            it.sets.forEach { set ->
                if (set.red > minRed) minRed = set.red
                if (set.blue > minBlue) minBlue = set.blue
                if (set.green > minGreen) minGreen = set.green
            }
            minRed * minGreen * minBlue
        }.toString()
    }
}

data class Game(val id: Int, val sets: List<CubeSet>)
data class CubeSet(val blue: Int, val green: Int, val red: Int)