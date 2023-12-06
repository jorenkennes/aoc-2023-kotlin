import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun `part 1 test example`() {
        val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        assertThat(Day04().part1(input)).isEqualTo(13)
    }

    @Test
    fun `part 2 test example`() {
        val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53            
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        assertThat(Day04().part2(input)).isEqualTo(30)
    }

    @Test
    fun `parse input to scratchcards`() {
        val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        assertThat(input.map { Scratchcard.parse(it) }).isEqualTo(
            listOf(
                Scratchcard(listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53)),
                Scratchcard(listOf(13, 32, 20, 16, 61), listOf(61, 30, 68, 82, 17, 32, 24, 19)),
                Scratchcard(listOf(1, 21, 53, 59, 44), listOf(69, 82, 63, 72, 16, 21, 14, 1)),
                Scratchcard(listOf(41, 92, 73, 84, 69), listOf(59, 84, 76, 51, 58, 5, 54, 83)),
                Scratchcard(listOf(87, 83, 26, 28, 32), listOf(88, 30, 70, 12, 93, 22, 82, 36)),
                Scratchcard(listOf(31, 18, 13, 56, 72), listOf(74, 77, 10, 23, 35, 67, 36, 11))
            )
        )
    }

    @Test
    fun `count winning numbers`() {
        val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        val scratchcards = input.map { Scratchcard.parse(it) }
        assertThat(scratchcards[0].countWinningNumbers()).isEqualTo(4)
        assertThat(scratchcards[1].countWinningNumbers()).isEqualTo(2)
        assertThat(scratchcards[2].countWinningNumbers()).isEqualTo(2)
        assertThat(scratchcards[3].countWinningNumbers()).isEqualTo(1)
        assertThat(scratchcards[4].countWinningNumbers()).isEqualTo(0)
        assertThat(scratchcards[5].countWinningNumbers()).isEqualTo(0)

    }
}