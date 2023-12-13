import java.util.*
import kotlin.time.measureTimedValue


fun main() {
    val input = readInput("Day08")
    val part1 = measureTimedValue { Day08().part1(input) }
    println("part1: ${part1.value} (${part1.duration})")
    val part2 = measureTimedValue { Day08().part2(input) }
    println("part2: ${part2.value} (${part2.duration})")
}

class Day08 {
    fun part1(input: List<String>): Int {
        val (instructionSet, listOfNodes) = parse(input)
        return Network(listOfNodes).navigate(instructionSet, "AAA")
    }

    fun part2(input: List<String>): Long {
        val (instructionSet, listOfNodes) = parse(input)
        return Network(listOfNodes).navigateAll(instructionSet)
    }
}


fun parse(lines: List<String>): Pair<InstructionSet, List<Node>> {
    val instructionSet = InstructionSet.parse(lines[0])
    val listOfNodes = lines.drop(2).map { Node.parse(it) }
    return instructionSet to listOfNodes
}

data class Network(val nodes: List<Node>) {

    fun navigateAll(instructions: InstructionSet): Long {
        val currentNodes = nodes.filter { node -> node.name.endsWith('A') }
        val steps = currentNodes.map { navigate(instructions, it.name) }.map { it.toLong() }
        return lcm(*steps.toLongArray())
    }

    fun navigate(instructions: InstructionSet, startNodeName: String): Int {
        var currentNode = nodes.first { node -> node.name == startNodeName }
        var steps = 0
        while (!currentNode.name.endsWith('Z')) {
            val instruction = instructions.getNext()
            val nextNodeName = findNextNodeName(instruction, currentNode)
            currentNode = nodes.first { node -> node.name == nextNodeName }
            steps++
        }
        return steps
    }

    private fun findNextNodeName(instruction: Instruction, node: Node): String {
        return when (instruction) {
            Instruction.LEFT -> node.left
            Instruction.RIGHT -> node.right
        }
    }
}

data class Node(val name: String, val left: String, val right: String) {

    companion object {
        fun parse(input: String): Node {
            val name = input.substringBefore(" = ")
            val nodes = input.substringAfter(" = ").removeSurrounding("(", ")")
            val left = nodes.split(", ")[0]
            val right = nodes.split(", ")[1]
            return Node(name, left, right)
        }
    }
}

data class InstructionSet(val instructions: List<Instruction>) {

    private var index = 0;

    fun getNext(): Instruction {
        val nextInstruction = instructions[index]
        if (index + 1 > instructions.lastIndex) index = 0 else index++
        return nextInstruction
    }

    companion object {
        fun parse(input: String): InstructionSet {
            return InstructionSet(input.map { when(it) {
                'L' -> Instruction.LEFT
                'R' -> Instruction.RIGHT
                else -> throw Exception("$it is not a valid instruction")
            } })
        }
    }
}

enum class Instruction {
    LEFT,
    RIGHT
}

private fun gcd(x: Long, y: Long): Long {
    return if (y == 0.toLong()) x else gcd(y, x % y)
}

fun lcm(vararg numbers: Long): Long {
    return Arrays.stream(numbers).reduce(1) { x: Long, y: Long -> x * (y / gcd(x, y)) }
}