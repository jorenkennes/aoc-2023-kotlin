import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day09Test {

    @Test
    fun `example puzzle input part 1`() {
        val input = """
            0 3 6 9 12 15
           1 3 6 10 15 21
           10 13 16 21 30 45""".trimIndent().lines()

        assertThat(Day09().part1(input)).isEqualTo(114)
    }

    @Test
    fun `puzzle input part 1`() {
        assertThat(Day09().part1(readInput("Day09"))).isEqualTo(2098530125)
    }

    @Test
    fun `example puzzle input part 2`() {
        val input = """
            0 3 6 9 12 15
           1 3 6 10 15 21
           10 13 16 21 30 45""".trimIndent().lines()

        assertThat(Day09().part2(input)).isEqualTo(2)
    }

    @Test
    fun `puzzle input part 2`() {
        assertThat(Day09().part2(readInput("Day09"))).isEqualTo(1016)
    }
}