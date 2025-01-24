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
    println("Input the length of the secret code:")
    val size = readln().toInt()
    println("Input the number of possible symbols in the code:")
    val numberOfSymbols = readln().toInt()
    var result = ""
    val uniqueCharacters: List<Char> = listOf('0'..'9', 'a'..'z').flatten().toList()
    val usableCharacters = uniqueCharacters.subList(0, numberOfSymbols).toMutableList()

    // generate secret code
    while (result.length < size) {
        result += usableCharacters.random()
        usableCharacters.remove(result[result.lastIndex])
    }

    val output = buildString {
        append("The secret is prepared: ")
        for (i in 1..size) {
            append("*")
        }
        append(" (0-")
        if (numberOfSymbols <= 10) {
            append(numberOfSymbols - 1)
            append(")")
        } else {
            append("9, a-")
            // size - 10 (for the digits) + 96 (offset to lower case a in ascii table)
            append((numberOfSymbols + 86).toChar())
            append(").")
        }
    }

    println(output)

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