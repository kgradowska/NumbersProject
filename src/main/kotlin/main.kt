fun main() {
    getNumbersFromUser()
}

private const val MAX_OPERATIONS = 6

private fun getNumbersFromUser() {
    var firstNumber: Int = -1
    var secondNumber: Int = -1
    var thirdNumber: Int = -1

    println("Give me 3 numbers!")
    println("Write first number and press Enter: ")
    while (firstNumber < 0) {
        try {
            firstNumber = getSingleNumberFromUser()
            if (firstNumber < 0) {
                println("This is not a correct number. Try again!")
            }
        } catch (e: java.lang.IllegalArgumentException) {
            println("This is not a correct number. Try again!")
        }
    }
    println("Czy liczba jest podzielna przez 3: ${ifNumberIsDividedByThree(firstNumber)}")
    println("Write second number and press Enter: ")
    while (secondNumber == -1) {
        try {
            secondNumber = getSingleNumberFromUser()
        } catch (e: java.lang.IllegalArgumentException) {
            println("This is not a correct number. Try again!")
        }
    }
    println("Czy liczba jest podzielna przez 3: ${ifNumberIsDividedByThree(secondNumber)}")

    println("Write third number and press Enter: ")
    while (thirdNumber == -1) {
        try {
            thirdNumber = getSingleNumberFromUser()
        } catch (e: java.lang.IllegalArgumentException) {
            println("This is not a correct number. Try again!")
        }
    }
    println("Czy liczba jest podzielna przez 3: ${ifNumberIsDividedByThree(thirdNumber)}")

    writeNumers(firstNumber, secondNumber, thirdNumber)

    val values1 = maxNumberSearching(firstNumber)
    val values2 = maxNumberSearching(secondNumber)
    val values3 = maxNumberSearching(thirdNumber)

    var numberIndex = arrayOf(0, 0)
    val numberIndex1 = ifModulo(firstNumber)
    val numberIndex2 = ifModulo(secondNumber)
    val numberIndex3 = ifModulo(thirdNumber)
    var maxSuma = 0
    var suma = 0
    var numberOne = 0
    var numberTwo = 0
    var numberThree = 0
    var ifTrue = false

    for (i in 0..1) {
        for (j in 0..1) {
            for (k in 0..1) {
                if (((values1[numberIndex1[i]] > -1) and (values2[numberIndex2[j]] > -1)) and (values3[numberIndex3[k]] > -1)) {
                    if ((numberIndex1[i] + numberIndex2[j] + numberIndex3[k]) <= 6) {
                        suma = values1[numberIndex1[i]] + values2[numberIndex2[j]] + values3[numberIndex3[k]]
                        println("Suma to: $suma a poszczególne liczby to: 1:  ${values1[numberIndex1[i]]}, 2:  ${values2[numberIndex2[j]]}, 3:  ${values3[numberIndex3[k]]}")
                        ifTrue =
                            ifNumberIsDividedByThree(values1[numberIndex1[i]]) and ifNumberIsDividedByThree(values2[numberIndex2[j]]) and ifNumberIsDividedByThree(
                                values3[numberIndex3[k]]
                            )
                        if ((suma > maxSuma) and ifTrue) {
                            maxSuma = suma
                            numberOne = values1[numberIndex1[i]]
                            numberTwo = values2[numberIndex2[j]]
                            numberThree = values3[numberIndex3[k]]
                            println("Max suma dla wartości $numberOne, $numberTwo i $numberThree to: $maxSuma")
                        }
                    } else println("Nie ma takiego zestawu liczb")
                }
            }
        }
    }
    println("Max suma dla wartości $numberOne, $numberTwo i $numberThree to: $maxSuma")
}

private fun getSingleNumberFromUser(): Int {
    val newNumber = readln().toInt()
    return newNumber
}

private fun writeNumers(firstNum: Int, secondNum: Int, thirdNum: Int) {
    println("[ $firstNum, $secondNum, $thirdNum ]")
}

private fun makeArrayOfNines(numberString: String): Array<Int> {
    val arrayName = Array(numberString.length, { i -> 9 })
    for (i in 0..arrayName.size - 1) {
        println(arrayName[i])
    }
    //val newArray = arrayName.toIntArray()
    val newArray: Array<Int> = arrayName.map { it.toInt() }.toTypedArray()
    return newArray
}

private fun makeArrayOfMaxDigits(numberString: String): Array<Int> {
    val array = changeIntToArrayOfInts(numberString.toInt())

    val arrayName: Array<Int> = Array(numberString.length, { i -> 9 })
    for (i in 0..array.size - 1) {
        if (array[i] < 3) {
            arrayName[i] = array[i] + 6
        }
    }

    for (i in 0..arrayName.size - 1) {
        println(arrayName[i])
    }
    //val newArray = arrayName.toIntArray()
    val newArray: Array<Int> = arrayName.map { it.toInt() }.toTypedArray()
    return newArray
}

private fun countChanges(arrayOfUserNumber: Array<Int>, arrayOfMaxNumber: Array<Int>): Int {
    var numberOfChanges = 0
    for (i in 0 until arrayOfMaxNumber.size) {
        if ((arrayOfMaxNumber[i] >= arrayOfUserNumber[i])) {
            numberOfChanges += arrayOfMaxNumber[i] - arrayOfUserNumber[i]
        } else {
            return MAX_OPERATIONS + 1 // means number is lower, this number won't work
        }
    }
    return numberOfChanges
}

private fun changeArrayOfIntsToInt(arrayToConvert: Array<Int>): Int {
    val stringnines: String = arrayToConvert.joinToString("")
    val result = stringnines.toInt()
    return result
}

private fun changeIntToArrayOfInts(intNumber: Int): Array<Int> {
    val firstNumberString = intNumber.toString()
    val array: Array<String> = firstNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val arrayInts: Array<Int> = array.map { it.toInt() }.toTypedArray()
    return arrayInts
}

private fun maxNumberSearching(firstNumber: Int): Array<Int> {
    val firstNumberString = firstNumber.toString()
    val array: Array<String> = firstNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val arrayInts: Array<Int> = array.map { it.toInt() }.toTypedArray()

    //tworzenie liczby z dziewiątek
    var firstNinesArray = makeArrayOfMaxDigits(firstNumberString)
    println("Number of characters: ${firstNinesArray.size}")

    var newMaxNumber: Int
    var numbersOfChanges = countChanges(arrayInts, firstNinesArray)
    println("Liczba zmian: $numbersOfChanges")
    newMaxNumber = changeArrayOfIntsToInt(firstNinesArray)
    println("First max number: $newMaxNumber")

    var firstDividedByThree: Int = -1
    val moduloResult = firstNumber.mod(3)
    if (moduloResult == 0) {
        firstDividedByThree = firstNumber
    }
    else {
        firstDividedByThree = -1
    }

    var values = arrayOf(firstDividedByThree, -1, -1, -1, -1, -1, -1)
    val mod1 = firstNumber.mod(3)
    println("Modulo: $mod1")

    while (newMaxNumber > firstNumber) {
        numbersOfChanges = countChanges(arrayInts, firstNinesArray)
        println("Liczba zmian: $numbersOfChanges")
        for (i in 1..6) {
            if (numbersOfChanges == i) {
                if (values[i] == -1) {
                    values[i] = newMaxNumber
                    println("Dla liczby zmian $numbersOfChanges największą liczbą jest $newMaxNumber")
                }
            }
        }
        newMaxNumber = newMaxNumber - 3
        println("Liczba po odjęciu: $newMaxNumber")
        firstNinesArray = changeIntToArrayOfInts(newMaxNumber)
    }
    for (number in values) {
        println("Array: $number")
    }
    return values
}

private fun ifModulo(number: Int): Array<Int> {
    var numberIndex = arrayOf(0, 0, 0)

    if (number.mod(3) == 0) {
        numberIndex[0] = 0
        numberIndex[1] = 3
        numberIndex[2] = 6
    } else if (number.mod(3) == 1) {
        numberIndex[0] = 0
        numberIndex[1] = 2
        numberIndex[2] = 5
    } else {
        numberIndex[0] = 0
        numberIndex[1] = 1
        numberIndex[2] = 4
    }
    return numberIndex
}

private fun ifNumberIsDividedByThree(number: Int): Boolean {
    val value = number.mod(3)
    return value == 0
}