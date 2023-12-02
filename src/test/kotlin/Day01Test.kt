import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun `Part1 test example`() {
        assertThat(part1(listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))).isEqualTo(142);
    }

    @Test
    fun `Part 2 test example`() {
        assertThat(
            part2(
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
            assertThat(combineFirstAndLastDigit("1abc2")).isEqualTo(12)
        }

        @Test
        fun `When digits are nested into other characters`() {
            assertThat(combineFirstAndLastDigit("pqr3stu8vwx")).isEqualTo(38)
        }

        @Test
        fun `When multiple digits are nested into other characters`() {
            assertThat(combineFirstAndLastDigit("a1b2c3d4e5f")).isEqualTo(15)
        }

        @Test
        fun `When only one digit exists`() {
            assertThat(combineFirstAndLastDigit("treb7uchet")).isEqualTo(77)
        }

        @Test
        fun `When only 2 digits`() {
            assertThat(combineFirstAndLastDigit("94")).isEqualTo(94)
        }

        @Test
        fun `When only no other characters`() {
            assertThat(combineFirstAndLastDigit("92374")).isEqualTo(94)
        }
    }

    @Nested
    @DisplayName("Combine first and last digit when digit can be spelled out into 2-digit number")
    inner class CombineFirstAndLastDigitWhenStringsCouldRepresentDigit {

        @Test
        fun `When one real digit and one spelled out before and after that digit `() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("two1nine")).isEqualTo(29);
        }

        @Test
        fun `When no real digits`() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("eightwothree")).isEqualTo(83);
        }

        @Test
        fun `When spelledOut digit combined with other letters`() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("abcone2threexyz")).isEqualTo(13);
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("xtwone3four")).isEqualTo(24);
        }

        @Test
        fun `When spelled out digits but first and last are regular digits`() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("4nineeightseven2")).isEqualTo(42);
        }

        @Test
        fun `When first digit is spelled out en last one is regular digit`() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("zoneight234")).isEqualTo(14);
        }

        @Test
        fun `When first digit is regular and last one is spelled out`() {
            assertThat(combineFirstAndLastDigitWhenStringsCouldRepresentDigit("7pqrstsixteen")).isEqualTo(76);
        }
    }
}