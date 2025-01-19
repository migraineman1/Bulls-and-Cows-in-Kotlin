package bullscows

import kotlin.random.Random

fun main() {
    var bulls = 0
    var cows = 0
    val secretCode = Random.nextInt(1000, 10000).toString()
    var guess = readln()

    for (i in 0..3) {
        if (guess[i] == secretCode[i]) { bulls++ }
    }

    for (i in 0..3) {
        for (j in 0..3) {
            if (guess[i] == secretCode[j]) { cows++ }
        }
    }

    cows = cows - bulls

    val output = buildString {
        append("Grade: ")
        if (bulls > 0) { append("$bulls bulls(s)") }
        if (bulls > 0 && cows > 0) { append(" and ") }
        if (cows > 0) { append("$cows cows(s)") }
        if (cows == 0 && bulls == 0) {append("None") }
        append(". The secret code is $secretCode.")
    }

    println(output)

}