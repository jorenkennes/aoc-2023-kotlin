import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readInput(name: String) = Path("src/main/resources/$name.txt").readLines()

fun String.splitToIntList() = this.trim().split(" ").filter { it != "" }.map { it.toInt() }

fun <T> List<T>.multiplyOf(selector: (T) -> Int): Int {
    var multiplication: Int = 1
    for (element in this) {
        multiplication *= selector(element)
    }
    return multiplication
}
