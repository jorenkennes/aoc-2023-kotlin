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
            .sumOf { "${findFirstDigit(it)}${findLastDigit(it)}".toInt() }
    }

    fun combineFirstAndLastDigit(line: String): Int {
        val firstDigit = line.first { it.isDigit() }
        val lastDigit = line.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    fun findFirstDigit(line: String): Char {
        val numberAsStringOrDigit = line.findAnyOf(numbers)?.second!!
        return stringDigitMap[numberAsStringOrDigit]!!
    }

    fun findLastDigit(line: String): Char {
        val numberAsStringOrDigit = line.findLastAnyOf(numbers)?.second!!
        return stringDigitMap[numberAsStringOrDigit]!!
    }

}

val numbers = listOf(
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
)

val stringDigitMap = mapOf(
    Pair("1", '1'),
    Pair("2", '2'),
    Pair("3", '3'),
    Pair("4", '4'),
    Pair("5", '5'),
    Pair("6", '6'),
    Pair("7", '7'),
    Pair("8", '8'),
    Pair("9", '9'),
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
