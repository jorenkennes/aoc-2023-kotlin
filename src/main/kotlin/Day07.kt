import java.lang.Exception
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("Day07")
    val part1 = measureTimedValue { Day07().part1(input) }
    println("part1: ${part1.value} (${part1.duration})")
    val part2 = measureTimedValue { Day07().part2(input) }
    println("part2: ${part2.value} (${part2.duration})")
}

class Day07 {
    fun part1(input: List<String>): Int {
        val hands = input.map { Hand.parse(it, isPart1 = true) }
        return hands.sortedWith(handComparator { cardRankMapperPart1(it) }).reversed()
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val hands = input.map { Hand.parse(it, isPart1 = false) }
        return hands.sortedWith(handComparator { cardRankMapperPart2(it) }).reversed()
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()
    }
}

fun handComparator(cardRankMapper: (char: Char) -> Int): Comparator<Hand> {
    return Comparator { hand1, hand2 ->
        if (hand1.handType != hand2.handType) {
            hand1.handType.compareTo(hand2.handType)
        } else {
            for (index in hand1.cards.indices) {
                if (hand1.cards[index] != hand2.cards[index]) {
                    return@Comparator cardRankMapper(hand2.cards[index]).compareTo(cardRankMapper(hand1.cards[index]))
                }
            }
            return@Comparator 0
        }
    }
}

data class Hand(val cards: List<Char>, val bid: Int, val isPart1: Boolean = true) {

    var handType: HandType

    init {
        this.handType = if (isPart1) {
            determineHandType(this.cards)
        } else {
            determineHandTypeWithJokers()
        }
    }

    private fun determineHandType(cards: List<Char>): HandType {
        val groupedBy = cards.groupBy { it }

        val groupSizes = groupedBy.entries.map { entry -> entry.value.size }.sortedDescending()
        return if (groupSizes.get(0) == 5) {
            HandType.fiveOfAKind
        } else if (groupSizes.get(0) == 4) {
            HandType.fourOfAKind
        } else if (groupSizes.get(0) == 3 && groupSizes.get(1) == 2) {
            HandType.fullHouse
        } else if (groupSizes.get(0) == 3) {
            HandType.threeOfAKind
        } else if (groupSizes.get(0) == 2 && groupSizes.get(1) == 2) {
            HandType.twoPair
        } else if (groupSizes.get(0) == 2) {
            HandType.onePair
        } else {
            HandType.highCard
        }
    }

    private fun determineHandTypeWithJokers(): HandType {
        val groupedBy = this.cards.groupBy { it }
        val cardRanks = groupedBy.entries
            .sortedByDescending { (_, value) -> value.size }
            .map { (key, _) -> key }

        return if (cardRanks.contains('J') && !cardRanks.all { it == 'J' }) {
            val replaceableCardRanks = cardRanks.filter { it != 'J' }
            replaceableCardRanks
                .map { cardRank -> determineHandType(this.cards.joinToString("").replace('J', cardRank).toList()) }
                .minOf { it }
        } else {
            determineHandType(this.cards)
        }
    }

    companion object {
        fun parse(line: String, isPart1: Boolean): Hand {
            val cards = line.split(" ")[0].toCharArray().toList()
            val bet = line.split(" ")[1].toInt()
            return Hand(cards, bet, isPart1)
        }
    }
}

fun cardRankMapperPart1(cardRank: Char): Int {
    return when(cardRank) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> 11
        'T' -> 10
        '9' -> 9
        '8' -> 8
        '7' -> 7
        '6' -> 6
        '5' -> 5
        '4' -> 4
        '3' -> 3
        '2' -> 2
        else -> throw Exception("CardRank $cardRank can not be mapped")
    }
}

fun cardRankMapperPart2(cardRank: Char): Int {
    return when(cardRank) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'T' -> 10
        '9' -> 9
        '8' -> 8
        '7' -> 7
        '6' -> 6
        '5' -> 5
        '4' -> 4
        '3' -> 3
        '2' -> 2
        'J' -> 1
        else -> throw Exception("CardRank $cardRank can not be mapped")
    }
}

enum class HandType {
    fiveOfAKind,
    fourOfAKind,
    fullHouse,
    threeOfAKind,
    twoPair,
    onePair,
    highCard
}
