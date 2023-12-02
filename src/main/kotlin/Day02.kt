fun main() {
    val input = readInput("Day02")
    println(Day02().part1(input))
    println(Day02().part2(input))
}

class Day02 {
    fun part1(input: List<String>): Int {
        val games = input.map { Game.parse(it) }
        return games.filter { game -> game.isPossible(12, 13, 14) }
            .sumOf { game -> game.id }
    }

    fun part2(input: List<String>): Int {
        val games = input.map { Game.parse(it) }
        return games.sumOf { game -> game.getMinOfCubesNeededRed() * game.getMinOfCubesNeededGreen() * game.getMinOfCubesNeededBlue() }
    }
}

data class Game(val id: Int, val grabs: List<Grab>) {

    companion object {
        fun parse(value: String): Game {
            val id = value.split(':').first().replaceFirst("Game ", "").toInt()
            val grabs = value.split(':').last()
                .split(';')
                .map { Grab.parse(it.trim()) }
            return Game(id, grabs);
        }
    }

    fun isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
        val red = getMinOfCubesNeededRed()
        val green = getMinOfCubesNeededGreen()
        val blue = getMinOfCubesNeededBlue()
        return maxRed >= red && maxGreen >= green && maxBlue >= blue
    }

    fun getMinOfCubesNeededRed(): Int {
        return grabs.maxOf { it.red }
    }

    fun getMinOfCubesNeededGreen(): Int {
        return grabs.maxOf { it.green }
    }

    fun getMinOfCubesNeededBlue(): Int {
        return grabs.maxOf { it.blue }
    }
}

data class Grab(val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
    companion object {
        fun parse(value: String): Grab {
            val colors = value.split(',').map { colorWithCount ->
                val count = colorWithCount.trim().split(" ").first().toInt()
                val color = colorWithCount.trim().split(" ").last()
                Pair(color, count)
            }
            val red = colors.find { colorCount -> colorCount.first == "red" }?.second ?: 0
            val green = colors.find { colorCount -> colorCount.first == "green" }?.second ?: 0
            val blue = colors.find { colorCount -> colorCount.first == "blue" }?.second ?: 0
            return Grab(red, green, blue)
        }
    }
}

