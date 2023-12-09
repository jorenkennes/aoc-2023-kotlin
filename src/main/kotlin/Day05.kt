import kotlin.math.min
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("Day05")
    val part1 = measureTimedValue { Day05().part1(input) }
    println("part1: ${part1.value} (${part1.duration})")
    val part2 = measureTimedValue { Day05().part2(input) }
    println("part2: ${part2.value} (${part2.duration})")
}

class Day05 {

    fun part1(input: List<String>): Long {
        val almanac = Almanac.parse(input)
        val locations = almanac.seeds.map { seed -> almanac.calculateLocation(seed) }
        return locations.min()
    }

    fun part2(input: List<String>): Long {
        val almanac = Almanac.parse(input)
        val seedPairs = almanac.seeds.windowed(2, 2).map { it[0] to it[1]}
        val locations = seedPairs.map { almanac.calculateLocationForSeedPair(it) }
        return locations.min()
    }
}

data class Almanac(val seeds: List<Long>, val maps: List<Map>) {
    companion object {
        fun parse(input: List<String>): Almanac {
            val seeds = input[0].substringAfter(": ").split(" ").map { it.toLong() }
            val seedToSoilMap = Map.parse(
                input.subList(
                    input.indexOf("seed-to-soil map:") + 1,
                    input.indexOf("soil-to-fertilizer map:") - 1
                )
            )
            val soilToFertilizerMap = Map.parse(
                input.subList(
                    input.indexOf("soil-to-fertilizer map:") + 1,
                    input.indexOf("fertilizer-to-water map:") - 1
                )
            )
            val fertilizerToWaterMap = Map.parse(
                input.subList(
                    input.indexOf("fertilizer-to-water map:") + 1,
                    input.indexOf("water-to-light map:") - 1
                )
            )
            val waterToLightMap = Map.parse(
                input.subList(
                    input.indexOf("water-to-light map:") + 1,
                    input.indexOf("light-to-temperature map:") - 1
                )
            )
            val lightToTemperatureMap = Map.parse(
                input.subList(
                    input.indexOf("light-to-temperature map:") + 1,
                    input.indexOf("temperature-to-humidity map:") - 1
                )
            )
            val temperatureToHumidityMap = Map.parse(
                input.subList(
                    input.indexOf("temperature-to-humidity map:") + 1,
                    input.indexOf("humidity-to-location map:") - 1
                )
            )
            val humidityToLocationMap = Map.parse(input.subList(input.indexOf("humidity-to-location map:") + 1, input.size))
            return Almanac(
                seeds,
                listOf(
                    seedToSoilMap,
                    soilToFertilizerMap,
                    fertilizerToWaterMap,
                    waterToLightMap,
                    lightToTemperatureMap,
                    temperatureToHumidityMap,
                    humidityToLocationMap
                )
            )
        }
    }

    fun calculateLocation(seed: Long): Long {
        var intermediateValue = seed
        for (map in maps) {
            intermediateValue = map.transform(intermediateValue)
        }
        return intermediateValue
    }

    fun calculateLocationForSeedPair(seedPair: Pair<Long, Long>): Long {
        var minValue = Long.MAX_VALUE
        for (seed in (seedPair.first..<seedPair.first + seedPair.second)) {
            minValue = min(minValue, calculateLocation(seed))
        }
        return minValue
    }
}


data class Map(val mapLines: List<MapLine>) {

    companion object {
        fun parse(value: List<String>): Map {
            return Map(value.map { MapLine.parse(it) })
        }
    }

    fun transform(input: Long): Long {
        val mapLine = this.mapLines.find { (it.source..it.source + it.range - 1).contains(input) }
        return mapLine?.mapValue(input) ?: input
    }

}

data class MapLine(val source: Long, val destination: Long, val range: Long) {

    companion object {
        fun parse(value: String): MapLine {
            val values = value.trim().split(" ")
            return MapLine(destination = values[0].toLong(), source = values[1].toLong(), range = values[2].toLong())
        }
    }

    fun mapValue(input: Long): Long {
        return destination + (input - source);
    }
}