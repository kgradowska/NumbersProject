import kotlin.math.min

private const val MAX_OPERATIONS = 6

fun main() {
    val userNumbers: UserNumbers = getNumbersFromUser()

    val values1 = countValues(userNumbers.first)
    val values2 = countValues(userNumbers.second)
    val values3 = countValues(userNumbers.third)

    val listOfPossibleResults = findBestResult(values1, values2, values3)
    println(listOfPossibleResults[0].sum)
    for (i in 0 until   listOfPossibleResults.size) {
        if(listOfPossibleResults[i].sum == listOfPossibleResults[0].sum) {
            println("[ ${listOfPossibleResults[i].num1}, ${listOfPossibleResults[i].num2}, ${listOfPossibleResults[i].num3} ]")
        }
    }
}

private fun getNumbersFromUser() : UserNumbers {
    println("Give me 3 numbers!")

    println("Write first number and press Enter: ")
    val firstNumber = getSingleNumberFromUser()

    println("Write second number and press Enter: ")
    val secondNumber = getSingleNumberFromUser()

    println("Write third number and press Enter: ")
    val thirdNumber = getSingleNumberFromUser()

    writeNumbers(firstNumber, secondNumber, thirdNumber)

    return UserNumbers(firstNumber, secondNumber, thirdNumber)
}

private fun countValues(number: Int) : Array<Int>{
    val maxAmountOfChanges = countChanges(number, makeArrayOfNines(number))

    // todo: test
    val listOptions: MutableList<Int> = checkPossibleOptions(number, maxAmountOfChanges)
    for (item in listOptions) {
        println(item)
    }

    var currentNumber = makeArrayOfMaxDigits(number)
    val values = arrayOf(-1, -1, -1, -1, -1, -1, -1)

    while(currentNumber >= number) {
        val amountOfChanges = countChanges(number, currentNumber)
        if (listOptions.contains(amountOfChanges)){
            if (values[amountOfChanges] == -1) {
                values[amountOfChanges] = currentNumber

                var hasAllValues = true
                for (item in listOptions) {
                    if (values[item] == -1) {
                        hasAllValues = false
                        break
                    }
                }

                if (hasAllValues){
                    for (item in values) {
                        println(item)
                    }
                    return values
                }
            }
        }
        currentNumber -= 3
    }
    return values
}

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

private fun makeArrayOfNines(number: Int): Int {
    val numberString = number.toString()
    //val array = changeIntToArrayOfInts(numberString.toInt())

    val maxNumber: Array<Int> = Array(numberString.length,{0})
    for ((index, s) in numberString.withIndex()) { //
        maxNumber[index] =  9 //
    }
    println(changeArrayOfIntsToInt(maxNumber))
    return changeArrayOfIntsToInt(maxNumber)
}

private fun makeArrayOfMaxDigits(number: Int): Int {
    val numberString = number.toString()
    val maxNumber: Array<Int> = Array(numberString.length,{0})
    for ((index, s) in numberString.withIndex()) { //
        maxNumber[index] =  min(9, s.toString().toInt() + 6) //
    }

    var maxNumberInt = changeArrayOfIntsToInt(maxNumber)
    if(maxNumberInt.mod(3) != 0){
        maxNumberInt = addSthToMaxValue(maxNumberInt)
    }
    return maxNumberInt
}

private fun countChanges(userNumber: Int, currentNumber: Int): Int {
    val userNumberString = userNumber.toString()
    val userArray: Array<String> = userNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val userArrayInts: Array<Int> = userArray.map { it.toInt() }.toTypedArray()

    val currentNumberString = currentNumber.toString()
    val currentArray: Array<String> = currentNumberString.toCharArray().map { it.toString() }.toTypedArray()
    val currentArrayInts: Array<Int> = currentArray.map { it.toInt() }.toTypedArray()

    //(arrayOfUserNumber: Array<Int>, arrayOfMaxNumber: Array<Int>): Int
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

private fun addSthToMaxValue(number: Int) : Int{
    var result = number
    if (number.mod(3) == 1) {
        result = number + 2
    }
    else if (number.mod(3) == 2) {
        result = number + 1
    }
    return result
}

private fun checkPossibleOptions(number: Int, maxNumberOfChanges: Int): MutableList<Int> {
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
    }
    else if (numberOfOptions == 1) {
        if (maxNumberOfChanges >= 2) {
            optionsList.add(2)
        }
        if (maxNumberOfChanges >= 5) {
            optionsList.add(5)
        }
    }
    else {
        if (maxNumberOfChanges >= 1) {
            optionsList.add(1)
        }
        if (maxNumberOfChanges >= 4) {
            optionsList.add(4)
        }
    }
    return optionsList
}

private fun findBestResult(val1: Array<Int>, val2: Array<Int>, val3: Array<Int>): MutableList<Result>{
    val resultList = mutableListOf<Result>()
    for (i in 0..6) {
        for (j in 0..6) {
            for (k in 0..6) {
                if ((val1[i]> -1) and (val2[j] > -1) and (val3[k] > -1) and ((i + j + k) <= 6)) {
                    resultList.add(Result(
                        numberOfChanges = i + j + k,
                        num1 = val1[i],
                        num2 = val2[j],
                        num3 = val3[k],
                        sum = val1[i] + val2[j] + val3[k]
                    ))
                }
            }
        }
    }
    resultList.sortByDescending { it.sum }
    //println(resultList)

    return resultList
}

private fun writeNumbers(firstNum: Int, secondNum: Int, thirdNum: Int) {
    println("[ $firstNum, $secondNum, $thirdNum ]")
}
