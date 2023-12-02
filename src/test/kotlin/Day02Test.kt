import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `part1 test input`() {
        val input = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        )

        assertThat(Day02().part1(input)).isEqualTo(8);
    }

    @Test
    fun `part2 test input`() {
        val input = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        )

        assertThat(Day02().part2(input)).isEqualTo(2286);
    }

    @Test
    fun `Should parse input to game object 1`() {
        val game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        assertThat(game.id).isEqualTo(1)
        assertThat(game.grabs).isEqualTo(listOf(
            Grab(blue = 3, red = 4),
            Grab(red = 1, green = 2, blue = 6),
            Grab(green = 2)
        ))
    }

    @Test
    fun `Should parse input to game object 10`() {
        val game = Game.parse("Game 10: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        assertThat(game.id).isEqualTo(10)
        assertThat(game.grabs).isEqualTo(listOf(
            Grab(blue = 3, red = 4),
            Grab(red = 1, green = 2, blue = 6),
            Grab(green = 2)
        ))
    }

    @Test
    fun `Should parse input to game object 68`() {
        val game = Game.parse("Game 68: 14 red, 10 green, 8 blue; 11 red, 1 blue, 6 green; 7 red, 7 green; 12 blue, 10 green, 3 red; 6 red, 12 blue, 10 green; 8 green, 14 red, 3 blue")
        assertThat(game.id).isEqualTo(68)
        assertThat(game.grabs).isEqualTo(listOf(
            Grab(red = 14, green = 10, blue = 8),
            Grab(red = 11, blue = 1, green = 6),
            Grab(red = 7, green = 7),
            Grab(blue = 12, green = 10 , red = 3),
            Grab(red = 6, blue = 12, green = 10),
            Grab(green = 8, red = 14, blue = 3),
        ))
    }

    @Test
    fun `game should not be possible`() {
        val game = Game.parse("Game 68: 14 red, 10 green, 8 blue; 11 red, 1 blue, 6 green; 7 red, 7 green; 12 blue, 10 green, 3 red; 6 red, 12 blue, 10 green; 8 green, 14 red, 3 blue")
        assertThat(game.isPossible(12, 13, 14)).isEqualTo(false)

    }
}