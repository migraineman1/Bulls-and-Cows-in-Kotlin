package bullscows

import kotlin.random.Random

fun gradeGuess() {
    var bulls = 0
    var cows = 0
    val secretCode = Random.nextInt(1000, 10000).toString()
    var guess = readln()

    for (i in 0..3) {
        if (guess[i] == secretCode[i]) {
            bulls++
        }
    }

    for (i in 0..3) {
        for (j in 0..3) {
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
        append(". The secret code is $secretCode.")
    }

    println(output)
}

fun main() {
    val size = readln().toInt()
    var result = ""

    if (size > 10) {
        println("Error: can't generate a secret number with a length of $size because there aren't enough unique digits.")
    } else {
        val pseudoRandomNumber: String = System.nanoTime().toString()
        val uniqueDigits = mutableSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        var i = 0
        var j = pseudoRandomNumber.lastIndex

        while (i < size && j >= 0) {
            if (uniqueDigits.contains(pseudoRandomNumber[j])) {
                if (!(i == 0 && pseudoRandomNumber[j] == '0')) {
                    result += pseudoRandomNumber[j]
                    uniqueDigits.remove(pseudoRandomNumber[j])
                    i++
                    j--
                } else {
                    j--
                }
            }
        }
        // if not enough unique digits in pseudoRandomNumber fill with random entries from uniqueDigits
        while (result.length < size) {
            result += uniqueDigits.random()
            uniqueDigits.remove(result[result.lastIndex])
        }
    }

    println("The random secret number is $result")

}