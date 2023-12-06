package com.github.warriorzz.aoc

fun String.splitToIntList(): List<Int> {
    return this.split(":")[1].replace("\\W+".toRegex(), " ").split(" ").map { it.toInt() }
}

fun String.splitToIntListWithoutPoints(): List<Int> {
    return this.replace("\\W+".toRegex(), " ").split(" ").map { it.toInt() }
}

fun String.splitToLongList(): List<Long> {
    return this.split(":")[1].replace("\\W+".toRegex(), " ").split(" ").map { it.toLong() }
}

fun String.splitToLongListWithoutPoints(): List<Long> {
    return this.replace("\\W+".toRegex(), " ").split(" ").map { it.toLong() }
}
