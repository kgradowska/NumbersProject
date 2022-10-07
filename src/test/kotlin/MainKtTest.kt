import org.junit.jupiter.api.Assertions.*


internal class MainKtTest {

    @org.junit.jupiter.api.Test
    fun countChangesBetweenNumbers() {
        assertNotEquals(20, countChangesBetweenNumbers(1, 3))
        assertEquals(2, countChangesBetweenNumbers(1, 3))
        assertEquals(24, countChangesBetweenNumbers(111, 999))
        assertEquals(0, countChangesBetweenNumbers(111, 111))
    }

    @org.junit.jupiter.api.Test
    fun makeDividableByThree() {
        assertEquals(0, makeDividableByThree(0))
        assertEquals(3, makeDividableByThree(1))
        assertEquals(3, makeDividableByThree(2))
    }


    @org.junit.jupiter.api.Test
    fun getPossibleIncrementationOptions() {
        assertIterableEquals(mutableListOf(1, 4), getPossibleIncrementationOptions(2, 6))
        assertIterableEquals(mutableListOf(1), getPossibleIncrementationOptions(17, 2))
        assertIterableEquals(mutableListOf(2, 5), getPossibleIncrementationOptions(73, 8))
    }


    @org.junit.jupiter.api.Test
    fun incrementDigitsByMax() {
        assertEquals(78, incrementDigitsByMax(11))
        assertEquals(99, incrementDigitsByMax(58))
        assertEquals(9999999, incrementDigitsByMax(3464643))
        assertEquals(789999999, incrementDigitsByMax(123456789))
    }

    @org.junit.jupiter.api.Test
    fun changeDigitsToNines() {
        assertEquals(99999, changeDigitsToNines(34634))
        assertEquals(99, changeDigitsToNines(58))
        assertEquals(9999999, changeDigitsToNines(1212212))
    }


    @org.junit.jupiter.api.Test
    fun calculateValues() {
        assertIterableEquals(mutableListOf(-1, 24, -1, -1, 54, -1, -1), calculateValues(14).toMutableList())
        assertIterableEquals(mutableListOf(78, -1, -1, 99, -1, -1, -1), calculateValues(78).toMutableList())
        assertIterableEquals(mutableListOf(-1, 3, -1, -1, 6, -1, -1), calculateValues(2).toMutableList())
        assertIterableEquals(mutableListOf(-1, 999, -1, -1, -1, -1, -1), calculateValues(989).toMutableList())
        assertIterableEquals(mutableListOf(0, -1, -1, 3, -1, -1, 6), calculateValues(0).toMutableList())
        assertIterableEquals(mutableListOf(99, -1, -1, -1, -1, -1, -1), calculateValues(99).toMutableList())
    }

}