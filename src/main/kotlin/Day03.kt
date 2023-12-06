fun main() {
    val input = readInput("Day03")
    println(Day03().part1(input))
    println(Day03().part2(input))
}

class Day03 {

    fun part1(input: List<String>): Int {
        val schematic = EngineSchematic(input)
        val engineParts = schematic.findAllEngineParts()
        return engineParts
            .filter { enginePart -> schematic.findSurroundingSymbols(enginePart).isNotEmpty() }
            .sumOf { enginePart -> enginePart.partNumber }
    }

    fun part2(input: List<String>): Int {
        val schematic = EngineSchematic(input)
        val engineParts = schematic.findAllEngineParts()
        return engineParts
            .map { enginePart -> schematic.findSurroundingSymbols(enginePart) }
            .flatten()
            .groupingBy { it }.eachCount()
            .filter { it.value > 1 }.keys.toList()
            .sumOf { gear ->
                engineParts
                    .filter { engine -> schematic.findSurroundingSymbols(engine).contains(gear) }
                    .map { it.partNumber }
                    .reduce { acc, i -> acc * i }
            }

    }
}

class EngineSchematic(private val schematic: List<String>) {

    fun findAllEngineParts(): List<EnginePart> {
        val engineParts = mutableListOf<EnginePart>()
        var enginePart = findNextEnginePart(Coordinate(0, 0))
        while (enginePart != null) {
            engineParts.add(enginePart)
            val nextStartCoordinate = findNextCoordinate(enginePart.coordinates.last())
            if (nextStartCoordinate != null) {
                enginePart = findNextEnginePart(nextStartCoordinate)
            } else {
                return engineParts
            }
        }
        return engineParts
    }

    fun findNextCoordinate(previousCoordinate: Coordinate): Coordinate? {
        if (previousCoordinate.x + 1 <= this.schematic[0].lastIndex) {
            return Coordinate(previousCoordinate.x + 1, previousCoordinate.y)
        } else if (previousCoordinate.y + 1 <= this.schematic.lastIndex) {
            return Coordinate(0, previousCoordinate.y + 1)
        } else {
            return null
        }
    }

    fun findNextEnginePart(startCoordinate: Coordinate): EnginePart? {
        var startX = startCoordinate.x
        for (row in startCoordinate.y..schematic.lastIndex) {
            for(column in startX .. schematic[row].lastIndex) {
                val element = this.schematic[row][column]
                val isCurrentElementDigit = element.isDigit()
                if (isCurrentElementDigit) {
                    val coordinates = (column..this.schematic[row].lastIndex)
                        .takeWhile { x -> this.schematic[row][x].isDigit()  }
                        .map { x -> Coordinate(x, row) }
                    val enginePart = coordinates.map { (x, y) -> this.schematic[y][x] }.joinToString("").toInt()
                    return EnginePart(enginePart, coordinates)
                }
            }
            startX = 0
        }
        return null
    }


    fun findSurroundingSymbols(enginePart: EnginePart): List<Symbol> {
        val enginePartRow = enginePart.coordinates[0].y

        val coordinateBefore = Coordinate(enginePart.coordinates.first().x - 1, enginePartRow)
        val coordinateAfter = Coordinate(enginePart.coordinates.last().x + 1, enginePartRow)
        val fullRow = listOf(listOf(coordinateBefore), enginePart.coordinates, listOf(coordinateAfter)).flatten()
        val coordinatesAbove = fullRow.map {
            coordinate -> Coordinate(coordinate.x, coordinateBefore.y - 1)
        }
        val coordinatesBelow = fullRow.map {
                coordinate -> Coordinate(coordinate.x, coordinateBefore.y + 1)
        }

        val coordinatesAround = listOf(
            listOf(coordinateBefore),
            listOf(coordinateAfter),
            coordinatesAbove,
            coordinatesBelow
        ).flatten()

        return coordinatesAround
            .mapNotNull { c -> this.schematic.getOrNull(c.y)?.getOrNull(c.x)?.let { Symbol(it, c) } }
            .filter { symbol -> symbol.value != '.' && !symbol.value.isDigit() }

    }

}

data class EnginePart(val partNumber: Int, val coordinates: List<Coordinate>)

data class Symbol(val value: Char, val coordinate: Coordinate)

data class Coordinate(val x: Int, val y: Int)