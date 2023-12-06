import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    fun `part 1 example test`() {
        val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""".trimIndent()

        assertThat(Day03().part1(input.lines())).isEqualTo(4361)
    }

    @Test
    fun `find engine parts`() {
        val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""".trimIndent().lines()

        assertThat(EngineSchematic(input).findAllEngineParts().map { it.partNumber })
            .isEqualTo(listOf(467, 114, 35, 633, 617, 58, 592, 755, 664, 598))
    }

    @Test
    fun `find next coordinate`() {
        val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""".trimIndent().lines()

        val schematic = EngineSchematic(input)
        assertThat(schematic.findNextCoordinate(Coordinate(0, 0))).isEqualTo(Coordinate(1, 0))
        assertThat(schematic.findNextCoordinate(Coordinate(9, 0))).isEqualTo(Coordinate(0, 1))
    }

    @Test
    fun `find surrounding symbols`() {
        val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""".trimIndent().lines()

        val schematic = EngineSchematic(input)
        val enginePart = schematic.findNextEnginePart(Coordinate(0,0))!!
        assertThat(schematic.findSurroundingSymbols(enginePart)).isEqualTo(listOf(Symbol('*', Coordinate(3, 1))))
    }

    @Test
    fun `part 2 example test`() {
        val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""".trimIndent()

        assertThat(Day03().part2(input.lines())).isEqualTo(467835)
    }
}