import kotlin.math.min

private const val MAX_OPERATIONS = 6

fun main() {
    val userNumbers: Array<Int> = getNumbersFromUser()
    val results = calculateResults(userNumbers)

    println("Best result(s):")
    for (item in results) {
        println("[ ${item.num1}, ${item.num2}, ${item.num3} ]")
    }
}

// returning numbers for maximum sum
fun calculateResults(array: Array<Int>): MutableList<Result> {
    if (array.size != 3) {
        println("Wrong array size")
        return mutableListOf<Result>()
    }
    println("Calculating results in progress...")
    val values1 = calculateValues(array[0])
    val values2 = calculateValues(array[1])
    val values3 = calculateValues(array[2])

    val listOfPossibleResults = findAllResults(values1, values2, values3)
    val listOfBestResults = mutableListOf<Result>()

    for (i in 0 until listOfPossibleResults.size) {
        if (listOfPossibleResults[i].sum == listOfPossibleResults[0].sum) {
            listOfBestResults.add(listOfPossibleResults[i])
        }
    }
    return listOfBestResults
}

private fun getNumbersFromUser(): Array<Int> {
    println("Give me 3 numbers!")

    println("Write first number and press Enter: ")
    val firstNumber = getSingleNumberFromUser()

    println("Write second number and press Enter: ")
    val secondNumber = getSingleNumberFromUser()

    println("Write third number and press Enter: ")
    val thirdNumber = getSingleNumberFromUser()

    writeNumbers(firstNumber, secondNumber, thirdNumber)

    return arrayOf(firstNumber, secondNumber, thirdNumber)
}

// gets all possible incrementation options and calculates maximum possible value for each of these changes
private fun calculateValues(number: Int): Array<Int> {
    val maxAmountOfChanges = countChangesBetweenNumbers(number, changeDigitsToNines(number)) // e.g. changes from 123 to 999

    val listOptions: MutableList<Int> = getPossibleIncrementationOptions(number, maxAmountOfChanges) // how many times we can increment digits
    var currentNumber = incrementDigitsByMax(number) // every digit + MAX, e.g. 123 -> 789
    val values = arrayOf(-1, -1, -1, -1, -1, -1, -1)

    while (currentNumber >= number) {
        val amountOfChanges = countChangesBetweenNumbers(number, currentNumber)
        if (listOptions.contains(amountOfChanges)) {
            if (values[amountOfChanges] == -1) {
                values[amountOfChanges] = currentNumber

                var hasAllValues = true
                for (item in listOptions) {
                    if (values[item] == -1) {
                        hasAllValues = false
                        break
                    }
                }

                if (hasAllValues) {
                    return values
                }
            }
        }
        currentNumber -= 3
    }
    return values
}

// taking single number from user in console
private fun getSingleNumberFromUser(): Int {
    var number: Int = -1
    while (number < 0) {
        try {
            number = readln().toInt()
            if (number < 0) {
                println("This is not a correct number. Try again!")
            }
        } catch (e: java.lang.IllegalArgumentException) {
            println("This is not a correct number. Try again!")
        }
    }
    return number
}

// creating number from digits 9
private fun changeDigitsToNines(number: Int): Int {
    val numberString = number.toString()
    val maxNumber: Array<Int> = Array(numberString.length, { 9 })

    return changeArrayOfIntsToInt(maxNumber)
}

// adding MAX_OPERATIONS to every digit of user's number (or less if it would be greater than 9) and make sure new number is dividable by 3
private fun incrementDigitsByMax(number: Int): Int {
    val numberString = number.toString()
    val maxNumber: Array<Int> = Array(numberString.length, { 0 })

    for ((index, s) in numberString.withIndex()) {
        maxNumber[index] = min(9, s.toString().toInt() + MAX_OPERATIONS)
    }

    var maxNumberInt = changeArrayOfIntsToInt(maxNumber)
    if (maxNumberInt.mod(3) != 0) {
        maxNumberInt = makeDividableByThree(maxNumberInt)
    }
    return maxNumberInt
}

// counting amount of changes between two numbers: userNumber and currentNumber
private fun countChangesBetweenNumbers(userNumber: Int, currentNumber: Int): Int {
    val userNumberString = userNumber.toString()
    val userArray: Array<String> = userNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val userArrayInts: Array<Int> = userArray.map { it.toInt() }.toTypedArray()

    val currentNumberString = currentNumber.toString()
    val currentArray: Array<String> = currentNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val currentArrayInts: Array<Int> = currentArray.map { it.toInt() }.toTypedArray()

    var numberOfChanges = 0
    for (i in 0 until currentArrayInts.size) {
        if ((currentArrayInts[i] >= userArrayInts[i])) {
            numberOfChanges += currentArrayInts[i] - userArrayInts[i]
        } else {
            return MAX_OPERATIONS + 1 // means number is lower, this number won't work
        }
    }
    return numberOfChanges
}

private fun changeArrayOfIntsToInt(arrayToConvert: Array<Int>): Int {
    val newString: String = arrayToConvert.joinToString("")
    return newString.toInt()
}

// converting input to next possible number dividable by 3 (or returning original if it's already dividable)
private fun makeDividableByThree(number: Int): Int {
    var result = number
    if (number.mod(3) == 1) {
        result = number + 2
    } else if (number.mod(3) == 2) {
        result = number + 1
    }
    return result
}

// checking modulo of user's number and how many changes you can make thereupon
private fun getPossibleIncrementationOptions(number: Int, maxNumberOfChanges: Int): MutableList<Int> {
    val numberOfOptions = number.mod(3)
    val optionsList = mutableListOf<Int>()
    if (numberOfOptions == 0) {
        if (maxNumberOfChanges >= 0) {
            optionsList.add(0)
        }
        if (maxNumberOfChanges >= 3) {
            optionsList.add(3)
        }
        if (maxNumberOfChanges >= 6) {
            optionsList.add(6)
        }
    } else if (numberOfOptions == 1) {
        if (maxNumberOfChanges >= 2) {
            optionsList.add(2)
        }
        if (maxNumberOfChanges >= 5) {
            optionsList.add(5)
        }
    } else {
        if (maxNumberOfChanges >= 1) {
            optionsList.add(1)
        }
        if (maxNumberOfChanges >= 4) {
            optionsList.add(4)
        }
    }
    return optionsList
}

// finding values meeting requirement of maximum 6 changes for all numbers
private fun findAllResults(val1: Array<Int>, val2: Array<Int>, val3: Array<Int>): MutableList<Result> {
    val resultList = mutableListOf<Result>()
    for (i in 0..MAX_OPERATIONS) {
        for (j in 0..MAX_OPERATIONS) {
            for (k in 0..MAX_OPERATIONS) {
                if ((val1[i] > -1) and (val2[j] > -1) and (val3[k] > -1) and ((i + j + k) <= MAX_OPERATIONS)) {
                    resultList.add(
                        Result(
                            numberOfChanges = i + j + k,
                            num1 = val1[i],
                            num2 = val2[j],
                            num3 = val3[k],
                            sum = val1[i] + val2[j] + val3[k]
                        )
                    )
                }
            }
        }
    }
    resultList.sortByDescending { it.sum }
    return resultList
}

private fun writeNumbers(firstNum: Int, secondNum: Int, thirdNum: Int) {
    println("INPUT: [ $firstNum, $secondNum, $thirdNum ]")
}
