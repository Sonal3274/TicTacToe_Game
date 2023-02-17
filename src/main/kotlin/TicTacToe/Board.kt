package TicTacToe

import kotlin.math.pow

class Board(private val n: Int, private val maxN: Int = 10) {
    private val empty = "___"
    var moveCount = 0
    var isgameOver = false
    var grid = Array(n) { Array(n) { empty } }

    init {
        if (n > maxN) {
            throw IllegalArgumentException("n cannot be greater than $maxN")
        }
    }

    fun resetBoard() {
        grid = Array(n) { Array(n) { empty } }
    }

    fun printBoard() {
        grid.forEach { row ->
            row.forEach { element ->
                if (element == empty) {
                    print("|$element|")
                } else {
                    print("| $element |")
                }
            }
            println()
        }
        println()
    }

    fun placePiece(x: Int, y: Int, move: String) {
        if (!isgameOver && isPositionValid(x, y) && grid[x][y] == empty) {
            moveCount++
            grid[x][y] = move
            printBoard()
            isgameOver = isWinningMove(x, y, move) || isDraw()
            if (isgameOver && !isDraw()) {
                println("Winner winner chicken dinner!")
            } else if (isDraw()) {
                println("Draw!")
            }
        }
    }

    fun isPositionValid(x: Int, y: Int): Boolean {
        return ((x in 0 until n) && (y in 0 until n))
    }

    private fun isWinningMove(x: Int, y: Int, move: String): Boolean {
        //Check the row to see if the player is winning
        for (i in 0 until n) {
            if (grid[x][i] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }
        //Check the column
        for (i in 0 until n) {
            if (grid[i][y] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        //Check the diagonal
        for (i in 0 until n) {
            if (x == y) {
                for (i in 0 until n) {
                    if (grid[i][i] != move) {
                        break
                    }
                    if (i == n - 1) {
                        return true
                    }
                }
            }
        }

        //Check the anti-diagonal
        if (x + y == n - 1) {
            for (i in 0 until n) {
                if (grid[i][(n - 1) - i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }
        return false
    }

    fun isDraw(): Boolean {
        return (moveCount == (n.toDouble().pow(2) - 1).toInt())
    }

    fun resetGame() {
        resetBoard()
        isgameOver = false
        moveCount = 0
    }
}
