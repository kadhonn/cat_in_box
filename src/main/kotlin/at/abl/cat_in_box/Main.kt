package at.abl.cat_in_box


fun main() {
    runValidation()
}

fun runValidation() {
    var gameState = GameState()

    printGameState(gameState)
//    val myGuess = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val myGuess = listOf(2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 6, 5, 4, 3, 2)
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
