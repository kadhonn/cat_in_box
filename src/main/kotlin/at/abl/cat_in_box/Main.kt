package at.abl.cat_in_box


fun main() {
//    runValidation()
    runSolver()
}

fun runSolver() {
    val solutions = solve(20)
    println("raw solution count is ${solutions.size}")

    val groupedByDepth = solutions.groupBy { it.size }
    val filtered = groupedByDepth.mapValues { filter(it.value) }

    for (solutionGroup in filtered.entries.sortedBy { it.key }) {
        println("solutions with size ${solutionGroup.key}: ${solutionGroup.value.size}")
        for (solution in solutionGroup.value) {
            println(solution)
        }
    }
}

fun filter(solutions: List<List<Int>>): List<List<Int>> {
    var result = filterReversed(solutions)
    result = filterWildCardMoves(result)
    return result
}

fun filterWildCardMoves(solutions: MutableList<List<Int>>): MutableList<List<Int>> {
    val result = mutableListOf<List<Int>>()
    outerLoop@ for (solution1 in solutions) {
        var onlyOneDifferenceCount = 0
        var tmpResult: List<Int>? = null
        for (solution2 in solutions) {
            val (differenceCount, result) = difference(solution1, solution2)
            if (differenceCount == 1) {
                assert(tmpResult == null || result == tmpResult)
                tmpResult = result
                onlyOneDifferenceCount++
            }
        }
        if (onlyOneDifferenceCount == BOXES_COUNT - 1) {
            if (!result.contains(tmpResult!!)) {
                result.add(tmpResult)
            }
        } else {
            if (!result.contains(solution1)) {
                result.add(solution1)
            }
        }
    }
    return result
}

fun difference(solution1: List<Int>, solution2: List<Int>): Pair<Int, List<Int>> {
    var differenceCount = 0
    val result = mutableListOf<Int>()
    for (i in solution1.indices) {
        if (solution1[i] == solution2[i]) {
            result.add(solution1[i])
        } else {
            differenceCount++
            result.add(-1)
        }
    }
    return Pair(differenceCount, result)
}

private fun filterReversed(solutions: List<List<Int>>): MutableList<List<Int>> {
    val result = mutableListOf<List<Int>>()
    outerLoop@ for (solution in solutions) {
        for (acceptedSolution in result) {
            if (isReverse(solution, acceptedSolution)) {
                continue@outerLoop
            }
        }
        result.add(solution)
    }
    return result
}

private fun isReverse(solution: List<Int>, acceptedSolution: List<Int>): Boolean {
    for (i in solution.indices) {
        if (solution[i] + acceptedSolution[i] != BOXES_COUNT + 1) {
            return false
        }
    }
    return true
}

fun runValidation() {
    var gameState = GameState()

    printGameState(gameState)
//    val myGuess = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9)
//    val myGuess = listOf(2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 6, 5, 4, 3, 2)
    val myGuess = listOf(2, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 6, 5, 4, 3, 2)
    for (guess in myGuess) {
        printGuess(guess)
        gameState = gameState.guess(guess)
        printGameState(gameState)
        if (gameState.hasWon()) {
            println("Hurray!")
            break
        }
    }

}

fun printGuess(guess: Int) {
    for (i in 1..BOXES_COUNT) {
        print(" ")
        if (i == guess) {
            print("#")
        } else {
            print(" ")
        }
    }
    println()
}

fun printGameState(gameState: GameState) {
    for (i in 1..BOXES_COUNT) {
        print(" ")
        print(i)
    }
    println()
    for (i in 1..BOXES_COUNT) {
        print(" ")
        if (gameState.state.contains(i)) {
            print("?")
        } else {
            print("_")
        }
    }
    println()
    println()
}
