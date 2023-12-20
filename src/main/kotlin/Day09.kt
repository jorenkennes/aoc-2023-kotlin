import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("Day09")
    val part1 = measureTimedValue { Day09().part1(input) }
    println("part1: ${part1.value} (${part1.duration})")
    val part2 = measureTimedValue { Day09().part2(input) }
    println("part2: ${part2.value} (${part2.duration})")
}
class Day09 {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val sequences = calculateFullHistory(line.splitToIntList())
            val extrapolatedList = extrapolatedForward(sequences)
            return@map extrapolatedList.first().last()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val sequences = calculateFullHistory(line.splitToIntList())
            val extrapolatedList = extrapolatedBackwards(sequences)
            return@map extrapolatedList.first().first()
        }.sum()
    }
}

fun calculateFullHistory(originalSequence: List<Int>): List<List<Int>> {
    val mutableList = mutableListOf(originalSequence)
    var previousSequence = originalSequence
    while (!previousSequence.all { it == 0 }) {
        val newSequence = calculateNextSequence(previousSequence)
        mutableList.add(newSequence)
        previousSequence = newSequence
    }
    return mutableList
}

fun calculateNextSequence(values: List<Int>): List<Int> {
    return values
        .zipWithNext()
        .map { zip -> zip.second - zip.first }
}

fun extrapolatedForward(sequences: List<List<Int>>): List<List<Int>> {
    val mutableList = sequences.toMutableList()
    mutableList[mutableList.lastIndex] = sequences.last().plus(0)
    (mutableList.lastIndex - 1 downTo 0).forEach { index ->
        val newLastElement = mutableList[index].last() + mutableList[index + 1].last()
        mutableList[index] = mutableList[index].plus(newLastElement)
    }
    return mutableList
}

fun extrapolatedBackwards(sequences: List<List<Int>>): List<List<Int>> {
    val mutableList = sequences.toMutableList()
    mutableList[mutableList.lastIndex] = sequences.last().prepend(0)
    (mutableList.lastIndex - 1 downTo 0).forEach { index ->
        val newElement = mutableList[index].first() - mutableList[index + 1].first()
        mutableList[index] = mutableList[index].prepend(newElement)
    }
    return mutableList
}