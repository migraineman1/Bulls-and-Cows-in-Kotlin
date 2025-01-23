package bullscows

import kotlin.random.Random

fun gradeGuess(secretCode: String, guess: String): List<String> {
    val result: List<String>
    var bulls = 0
    var cows = 0
    var solved = false

    for (i in 0 until secretCode.length) {
        if (guess[i] == secretCode[i]) {
            bulls++
        }
    }

    for (i in 0 until guess.length) {
        for (j in 0 until secretCode.length) {
            if (guess[i] == secretCode[j]) {
                cows++
            }
        }
    }

    cows = cows - bulls

    val output = buildString {
        append("Grade: ")
        if (bulls > 0) {
            append("$bulls bulls(s)")
        }
        if (bulls > 0 && cows > 0) {
            append(" and ")
        }
        if (cows > 0) {
            append("$cows cows(s)")
        }
        if (cows == 0 && bulls == 0) {
            append("None")
        }
    }
    if (bulls == secretCode.length) {
        solved = true
    }
    result = listOf(output, solved.toString())
    return result
}

fun generateSecretCode(): String {
    println("Please, enter the secret code's length:")
    val size = readln().toInt()
    var result = ""

    if (size > 10) {
        println("Error: can't generate a secret number with a length of $size because there aren't enough unique digits.")
    } else {
        val uniqueDigits = mutableSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        // generate secret code
        while (result.length < size) {
            result += uniqueDigits.random()
            uniqueDigits.remove(result[result.lastIndex])
        }
    }
    return result
}

fun main() {
    var result: List<String> = listOf("", "false")
    val secretCode = generateSecretCode()
    var guess = ""
    println("Okay, let's start a game!")

    var i = 1
    while (result[1] == "false") {
        println("Turn $i:")
        guess = readln()
        result = gradeGuess(secretCode, guess)

        println(result[0])
        i++
    }

    println("Congratulations! You guessed the secret code.")

}