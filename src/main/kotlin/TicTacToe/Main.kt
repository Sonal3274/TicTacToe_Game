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

    if (size > 10) {
        println("Invalid board size. Please enter a size less than or equal to 10.")
        return
    }

    board = Board(size)
    board.printBoard()

    while (!board.isgameOver) {
        takeTurns()
        println("$currentPlayer's turn")
        println("Enter row number (type 'exit' to quit the game):")
        var input = scanner.nextLine()
        if (input.equals("exit", ignoreCase = true) || input.equals("quit", ignoreCase = true)) {
            println("Thanks for playing!")
            return
        }
        var row = Integer.parseInt(input)
        println("Enter column number (type 'exit' to quit the game):")
        input = scanner.nextLine()
        if (input.equals("exit", ignoreCase = true) || input.equals("quit", ignoreCase = true)) {
            println("Thanks for playing!")
            return
        }
        var col = Integer.parseInt(input)
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
