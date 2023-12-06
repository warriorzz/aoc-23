package com.github.warriorzz.aoc

class Day3 : Day(3) {

    var lines: List<ScematicLine> = listOf()

    override fun init() {
        lines = input.map { line ->
            val numbers = "\\d+".toRegex().findAll(line).map { result ->
                val int = result.value.toInt()
                val range = result.range
                val start = result.range.first
                NumberSc(int, range, start)
            }.toList()

            val symbols = ArrayList<Gear>()
            line.forEachIndexed { index, it ->
                if (!it.isDigit() && it != '.')
                    symbols.add(Gear(index, it == '*'))
            }
            ScematicLine(symbols, numbers)
        }
        lines.forEachIndexed { indexLine, scematicLine ->
            scematicLine.numbers.forEach { number ->
                if (indexLine != 0) {
                    lines[indexLine - 1].symbols.forEach {
                        if (it.index in number.range) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                        if (it.index == number.range.first - 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                        if (it.index == number.range.last + 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                    }
                }
                if (indexLine != lines.size - 1) {
                    lines[indexLine + 1].symbols.forEach {
                        if (it.index in number.range) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                        if (it.index == number.startIndex - 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                        if (it.index == number.range.last + 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                    }
                }
                if (number.range.first != 0) {
                    scematicLine.symbols.forEach {
                        if (it.index == number.startIndex - 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                    }
                }
                if (number.range.last != input.first().length - 1) {
                    scematicLine.symbols.forEach {
                        if (it.index == number.range.last + 1) {
                            number.hasSymbol = true
                            it.increaseBy(number.int)
                        }
                    }
                }
            }
        }

        lines.forEach { line ->
            line.symbols.forEach {
                println(it.index)
                println(it.isGear)
                println(it.gearRatio)
                println(it.count)
            }
        }
    }

    override val partOne: (List<String>, Boolean) -> String = { _, _ ->
        lines.map { it.numbers }.reduce { acc, numberScs ->
            val list = ArrayList<NumberSc>()
            list.addAll(acc)
            list.addAll(numberScs)
            list
        }.filter { it.hasSymbol }.sumOf { it.int }.toString()
    }

    override val partTwo: (List<String>, Boolean) -> String = { _, _ ->
        lines.map { it.symbols }.reduce { acc, symbol ->
            val list = ArrayList<Gear>()
            list.addAll(acc)
            list.addAll(symbol)
            list
        }.filter { it.isGear && it.count == 2 }.sumOf { it.gearRatio }.toString()
    }
}

data class ScematicLine(val symbols: List<Gear>, val numbers: List<NumberSc>)
data class NumberSc(val int: Int, val range: IntRange, val startIndex: Int, var hasSymbol: Boolean = false)
data class Gear(val index: Int, var isGear: Boolean, var gearRatio: Int = 1, var count: Int = 0) {
    fun increaseBy(value: Int) {
        if (isGear) {
            if (count in 0..1) {
                gearRatio *= value
                count += 1
            } else {
                isGear = false
            }
        }
    }
}