import kotlin.math.pow
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("Day04")
    val part1 = measureTimedValue { Day04().part1(input) }
    val part2 = measureTimedValue { (Day04().part2(input)) }
    println("part1: ${part1.value} (${part1.duration})")
    println("part2: ${part2.value} (${part2.duration})")
}

class Day04 {

    fun part1(input: List<String>): Int {
        return input
            .map { Scratchcard.parse(it) }
            .filter { it.countWinningNumbers() > 0 }
            .sumOf { 2.0.pow(it.countWinningNumbers() - 1) }
            .toInt()
    }

    fun part2(input: List<String>): Int {
        val originalScratchCards = input.map { Scratchcard.parse(it) }
        val scratchcardDecks = originalScratchCards.map { mutableListOf(it) }
        for (gameNr in originalScratchCards.indices) {
            val nrOfScratchcards = scratchcardDecks[gameNr].size
            val winningNumbers = scratchcardDecks[gameNr][0].countWinningNumbers()
            repeat(nrOfScratchcards) {
                ((gameNr + 1)..(gameNr + winningNumbers))
                    .forEach { scratchcardDecks[it].add(originalScratchCards[it].copy())
                }
            }
        }
        return scratchcardDecks.flatten().size
    }

}

data class Scratchcard(val winningNumbers: List<Int>, val myNumbers: List<Int>) {

    companion object {
        fun parse(line: String): Scratchcard {
            val numbers = line.substringAfter(":")
            val winningNumbers = numbers.split("|")[0].trim().split(" ").filter { it != "" }.map { it.toInt() }
            val myNumbers = numbers.split("|")[1].trim().split(" ").filter { it != "" }.map { it.toInt() }
            return Scratchcard(winningNumbers, myNumbers)
        }
    }

    fun countWinningNumbers(): Int {
        return this.winningNumbers.count { winningNumber -> myNumbers.contains(winningNumber) }
    }
}