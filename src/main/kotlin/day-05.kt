package com.github.warriorzz.aoc

class Day5 : Day(5) {

    override fun init() {
    }

    override val partOne: (List<String>, Boolean) -> String = { input, _ ->
        val seeds1 = input[0].split(": ")[1].split(" ").map { it.toLong() }
        val converterMaps1 = ArrayList<ArrayList<Triple<Long, Long, Long>>>()
        var current1 = 0
        converterMaps1.add(ArrayList())

        input.drop(3).forEach {
            if (it.isEmpty()) {
                current1++
                converterMaps1.add(ArrayList())
            } else if (it[0].isDigit()) {
                val split = it.split(" ").map { it.toLong() }
                converterMaps1[current1].add(Triple(split[1], split[0], split[2]))
            }
        }

        seeds1.map { seed ->
            var currentSeed = seed
            (0 until converterMaps1.size).forEach { i ->
                val converter = converterMaps1[i]
                for (x in converter) {
                    if (currentSeed in x.first..<x.first + x.third) {
                        currentSeed = currentSeed - x.first + x.second
                        return@forEach
                    }
                }
            }
            currentSeed
        }.min().toString()
    }

    override val partTwo: (List<String>, Boolean) -> String = { input, _ ->
        val seeds = input[0].split(": ")[1].split(" ").map { it.toLong() }
        val newSeed = ArrayList<LongRange>()
        var skip = false
        for (x in seeds.indices) {
            if (!skip) {
                newSeed.add(seeds[x] until seeds[x] + seeds[x + 1])
                skip = true
            } else {
                skip = false
            }
        }
        val converterMaps = ArrayList<ArrayList<Triple<Long, Long, Long>>>()
        var current = 0
        converterMaps.add(ArrayList())

        input.drop(3).forEach {
            if (it.isEmpty()) {
                current++
                converterMaps.add(ArrayList())
            } else if (it[0].isDigit()) {
                val split = it.split(" ").map { it.toLong() }
                converterMaps[current].add(Triple(split[1], split[0], split[2]))
            }
        }

        var min = Long.MAX_VALUE
        newSeed.parallelStream().forEach ignored@{ seedRange ->
            var small: Long = Long.MAX_VALUE
            seedRange.forEach forEachSmall@{ seed ->
                var currentSeed = seed
                (0 until converterMaps.size).forEach { i ->
                    val converter = converterMaps[i]
                    for (x in converter) {
                        if (currentSeed in x.first..<x.first + x.third) {
                            currentSeed = currentSeed - x.first + x.second
                            return@forEach
                        }
                    }
                }
                if (small > currentSeed) small = currentSeed
            }
            if (small < min) min = small
        }
        min.toString()
    }
}
