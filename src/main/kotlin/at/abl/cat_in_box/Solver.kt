package at.abl.cat_in_box

private class Solver(val maxDepth: Int) {
    private val solutions = mutableSetOf<List<Int>>()
    private val currentPath = mutableListOf<Int>()
    private val seenStates = mutableListOf<GameState>()

    fun run(): Set<List<Int>> {
        seenStates.add(GameState())
        runRecursive(0)

        return solutions
    }

    private fun runRecursive(currentDepth: Int) {
        if (currentDepth == maxDepth) {
            return
        }
        val lastGameState = seenStates.last()
        for (guess in 1..BOXES_COUNT) {
            val newState = lastGameState.guess(guess)
            if (!seenStates.contains(newState)) {
                seenStates.add(newState)
                currentPath.add(guess)
                if (newState.hasWon()) {
                    solutions.add(currentPath.toList())
                } else {
                    runRecursive(currentDepth + 1)
                }
                currentPath.removeLast()
                seenStates.removeLast()
            }
        }
    }

}

fun solve(maxDepth: Int): Set<List<Int>> {
    return Solver(maxDepth).run()
}