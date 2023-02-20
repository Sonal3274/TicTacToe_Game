package TicTacToe

import java.util.Scanner

private var board = Board(0)
private val player1 = "x"
private val player2 = "o"
private var currentPlayer = ""
private var answer = ""
fun main() {

    println("Hello, welcome to TicTacToe game")
    println("Please enter size of your board:")
    val scanner = Scanner(System.`in`)
    val size = Integer.parseInt(scanner.nextLine())
    try {
        val board = Board(11) // Try to create a board with n = 11 (which should throw an exception)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
    board = Board(size)
    board.printBoard()

    while (!board.isgameOver) {
        takeTurns()
        println("$currentPlayer's turn")
        println("Enter row number:")
        var row = Integer.parseInt(scanner.nextLine())
        println("Enter column number:")
        var col = Integer.parseInt(scanner.nextLine())
        board.placePiece(row - 1, col - 1, currentPlayer)
        if (board.isgameOver) {
            println("Do you want to play again? Type y or yes")
            var answer = scanner.nextLine()
            if (isPlayingAgain(answer)) {
                board.resetGame()
            }
        }
    }
}

fun takeTurns() {
    currentPlayer = if (player1 == currentPlayer) {
        player2
    } else {
        player1
    }
}

fun isPlayingAgain(answer: String): Boolean {
    return (answer.equals("y", ignoreCase = true)
            || answer.equals("yes", ignoreCase = true))
}