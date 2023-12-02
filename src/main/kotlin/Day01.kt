fun main() {
    val input = readInput("Day01")
    println(Day01().part1(input))
    println(Day01().part2(input))
}

class Day01 {

    fun part1(input: List<String>): Int {
        return input.sumOf { combineFirstAndLastDigit(it) }
    }

    fun part2(input: List<String>): Int {
        return input
            .sumOf { combineFirstAndLastDigitWhenStringsCouldRepresentDigit(it) }
    }

    fun combineFirstAndLastDigit(stringValue: String): Int {
        val firstDigit = stringValue.first { it.isDigit() }
        val lastDigit = stringValue.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    fun combineFirstAndLastDigitWhenStringsCouldRepresentDigit(stringValue: String): Int {
        return "${findFirstDigit(stringValue)}${findLastDigit(stringValue)}".toInt()
    }

    fun findFirstDigit(stringValue: String): Char {
        val indexOfFirstDigit = stringValue.indexOfFirst { it.isDigit() }.takeIf { it >= 0 } ?: stringValue.lastIndex
        val sectionBeforeFirstDigit = stringValue.substring(0, indexOfFirstDigit)
        for (i in sectionBeforeFirstDigit.indices) {
            val subSection = sectionBeforeFirstDigit.substring(0..i)
            val matchingNumber = digits.entries.firstOrNull { digit -> subSection.contains(digit.key) }
            if (matchingNumber != null) {
                return matchingNumber.value
            }
        }

        return stringValue[indexOfFirstDigit];
    }

    fun findLastDigit(stringValue: String): Char {
        val indexOfLastDigit = stringValue.indexOfLast { it.isDigit() }.takeIf { it >= 0 } ?: 0;
        val sectionAfterLastDigit = stringValue.substring(indexOfLastDigit + 1)
        for (i in sectionAfterLastDigit.lastIndex downTo 0) {
            val subSection = sectionAfterLastDigit.substring(i)
            val matchingNumber = digits.entries.firstOrNull { digit -> subSection.contains(digit.key) }
            if (matchingNumber != null) {
                return matchingNumber.value
            }
        }

        return stringValue[indexOfLastDigit];
    }

}

val digits = mapOf(
    Pair("one", '1'),
    Pair("two", '2'),
    Pair("three", '3'),
    Pair("four", '4'),
    Pair("five", '5'),
    Pair("six", '6'),
    Pair("seven", '7'),
    Pair("eight", '8'),
    Pair("nine", '9')
)
