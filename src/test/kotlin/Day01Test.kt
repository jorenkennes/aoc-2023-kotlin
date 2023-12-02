import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun `Part 1 puzzle input`() {
        val input = readInput("Day01")
        assertThat(Day01().part1(input)).isEqualTo(55017);
    }

    @Test
    fun `Part 2 puzzle input`() {
        val input = readInput("Day01")
        assertThat(Day01().part2(input)).isEqualTo(53539);
    }

    @Test
    fun `Part1 test example`() {
        assertThat(Day01().part1(listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))).isEqualTo(142);
    }

    @Test
    fun `Part 2 test example`() {
        assertThat(
            Day01().part2(
                listOf(
                    "two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
                    "7pqrstsixteen"
                )
            )
        ).isEqualTo(281);
    }


    @Nested
    @DisplayName("Combine first and last digit into 2-digit number")
    inner class CombineFirstAndLastDigit {

        @Test
        fun `When first and last character is digit Then combine to 2-digit number`() {
            assertThat(Day01().combineFirstAndLastDigit("1abc2")).isEqualTo(12)
        }

        @Test
        fun `When digits are nested into other characters`() {
            assertThat(Day01().combineFirstAndLastDigit("pqr3stu8vwx")).isEqualTo(38)
        }

        @Test
        fun `When multiple digits are nested into other characters`() {
            assertThat(Day01().combineFirstAndLastDigit("a1b2c3d4e5f")).isEqualTo(15)
        }

        @Test
        fun `When only one digit exists`() {
            assertThat(Day01().combineFirstAndLastDigit("treb7uchet")).isEqualTo(77)
        }

        @Test
        fun `When only 2 digits`() {
            assertThat(Day01().combineFirstAndLastDigit("94")).isEqualTo(94)
        }

        @Test
        fun `When only no other characters`() {
            assertThat(Day01().combineFirstAndLastDigit("92374")).isEqualTo(94)
        }
    }
}