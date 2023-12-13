import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {

    val input = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent().lines()

    @Test
    fun `part 1 test input`() {
        assertThat(Day07().part1(input)).isEqualTo(6440)
    }

    @Test
    fun `part 1 puzzle input`() {
        assertThat(Day07().part1(readInput("Day07"))).isEqualTo(249483956)
    }

    @Test
    fun `part 2 puzzle input`() {
        assertThat(Day07().part2(readInput("Day07"))).isEqualTo(252137472)
    }

    @Test
    fun `parse input`() {
        assertThat(input.map { Hand.parse(it, isPart1 = true) }).isEqualTo(
            listOf(
                Hand(listOf('3', '2', 'T', '3', 'K'), 765),
                Hand(listOf('T', '5', '5', 'J', '5'), 684),
                Hand(listOf('K', 'K', '6', '7', '7'), 28),
                Hand(listOf('K', 'T', 'J', 'J', 'T'), 220),
                Hand(listOf('Q', 'Q', 'Q', 'J', 'A'), 483)
            )
        )
    }

    @Test
    fun `determine HandType`() {
        assertThat(Hand(listOf('3', '2', 'T', '3', 'K'), 765).handType).isEqualTo(HandType.onePair)
        assertThat(Hand(listOf('T', '5', '5', 'J', '5'), 684).handType).isEqualTo(HandType.threeOfAKind)
        assertThat(Hand(listOf('K', 'K', '6', '7', '7'), 28).handType).isEqualTo(HandType.twoPair)
        assertThat(Hand(listOf('K', 'T', 'J', 'J', 'T'), 220).handType).isEqualTo(HandType.twoPair)
        assertThat(Hand(listOf('Q', 'Q', 'Q', 'J', 'A'), 483).handType).isEqualTo(HandType.threeOfAKind)
    }

    @Test
    fun `determine rank`() {
        assertThat(
            listOf(
                Hand(listOf('3', '2', 'T', '3', 'K'), 765),
                Hand(listOf('T', '5', '5', 'J', '5'), 684),
                Hand(listOf('K', 'K', '6', '7', '7'), 28),
                Hand(listOf('K', 'T', 'J', 'J', 'T'), 220),
                Hand(listOf('Q', 'Q', 'Q', 'J', 'A'), 483)
            ).sortedWith(handComparator { cardRankMapperPart1(it) })
        ).isEqualTo(
            listOf(
                Hand(listOf('Q', 'Q', 'Q', 'J', 'A'), 483),
                Hand(listOf('T', '5', '5', 'J', '5'), 684),
                Hand(listOf('K', 'K', '6', '7', '7'), 28),
                Hand(listOf('K', 'T', 'J', 'J', 'T'), 220),
                Hand(listOf('3', '2', 'T', '3', 'K'), 765)
            )
        )
    }
}