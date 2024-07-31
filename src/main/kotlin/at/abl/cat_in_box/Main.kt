package at.abl.cat_in_box


fun main() {
    runValidation()
}

fun runValidation() {
    var gameState = GameState()

    printGameState(gameState)
//    val myGuess = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val myGuess = listOf(2, 2, 3, 4, 5, 6, 7, 7, 9, 9, 8, 7, 6, 5, 4, 3, 2)
    for (guess in myGuess) {
        gameState = gameState.guess(guess)
        println("guessing $guess")
        printGameState(gameState)
        if (gameState.hasWon()) {
            println("Hurray!")
            break
        }
    }

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
