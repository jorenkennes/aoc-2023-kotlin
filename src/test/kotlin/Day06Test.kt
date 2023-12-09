import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

    val testInput = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

    @Test
    fun `Part 1 test input`() {
        assertThat(Day06().part1(testInput)).isEqualTo(288)
    }

    @Test
    fun `Part 2 test input`() {
        assertThat(Day06().part2(testInput)).isEqualTo(71503)
    }

    @Test
    fun `parse input as multiple races`() {
        val races = Race.parseAsMultipleRaces(testInput)
        assertThat(races).isEqualTo(listOf(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        ))
    }

    @Test
    fun `parse input as single race`() {
        val races = Race.parseAsSingleRace(testInput)
        assertThat(races).isEqualTo(Race(71530, 940200))
    }
}