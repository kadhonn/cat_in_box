package at.abl.cat_in_box

const val BOXES_COUNT = 10

class GameState private constructor(val state: Set<Int>) {

    constructor() : this(fullState(BOXES_COUNT))

    fun guess(box: Int): GameState {
        val stateWithoutGuess = state.toMutableSet()
        stateWithoutGuess.remove(box)

        return moveCat(stateWithoutGuess)
    }

    fun hasWon(): Boolean {
        return state.isEmpty()
    }

    private fun moveCat(stateWithoutGuess: Set<Int>): GameState {
        val newState = mutableSetOf<Int>()

        for (i in stateWithoutGuess) {
            when (i) {
                1 -> {
                    newState.add(2)
                }

                BOXES_COUNT -> {
                    newState.add(BOXES_COUNT - 1)
                }

                else -> {
                    newState.add(i - 1)
                    newState.add(i + 1)
                }
            }
        }

        return GameState(newState)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        return state == other.state
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }


}

private fun fullState(boxesCount: Int): Set<Int> {
    val set = mutableSetOf<Int>()
    for (i in 1..boxesCount) {
        set.add(i)
    }
    return set
}
