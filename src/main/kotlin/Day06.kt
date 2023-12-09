import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("Day06")
    val part1 = measureTimedValue { Day06().part1(input) }
    println("part1: ${part1.value} (${part1.duration})")
    val part2 = measureTimedValue { Day06().part2(input) }
    println("part2: ${part2.value} (${part2.duration})")
}

class Day06 {
    fun part1(input: List<String>): Int {
        val races = Race.parseAsMultipleRaces(input)
        return races.multiplyOf { race -> race.findWinningRaceStrategies().size }
    }

    fun part2(input: List<String>): Int {
        val race = Race.parseAsSingleRace(input)
        return race.findWinningRaceStrategies().size
    }
}

data class Race(val time: Long, val distance: Long) {
    companion object {
        fun parseAsMultipleRaces(input: List<String>): List<Race> {
            val times = input[0].substringAfter("Time: ").splitToIntList()
            val distances = input[1].substringAfter("Distance: ").splitToIntList()
            return times.zip(distances).map { Race(it.first.toLong(), it.second.toLong()) }
        }

        fun parseAsSingleRace(input: List<String>): Race {
            val time = input[0].substringAfter("Time: ").filter { it != ' ' }.toLong()
            val distance = input[1].substringAfter("Distance: ").filter { it != ' ' }.toLong()
            return Race(time, distance)
        }
    }

    fun findWinningRaceStrategies(): List<RaceStrategy> {
        val raceStrategies = (0..time).map { RaceStrategy(it, time) }
        return raceStrategies.filter { it.calculateDistanceTraveled() > distance }
    }
}

data class RaceStrategy(val chargeTime: Long, val raceTime: Long) {

    fun calculateDistanceTraveled(): Long {
        val moveTime = raceTime - chargeTime
        val distanceTraveled = chargeTime * moveTime
        return distanceTraveled
    }
}