package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(){
    runBlocking {
        println("Calling 1st val")
        val getFirstDiffered = async { getFirstVal() }
        println("Calling 2nd val")
        val getSecondDiffered = async { getSecondVal() }

        println("Waiting for 1st val")
        val mFirstVal = getFirstDiffered.await()
        println("Received 1st val: $mFirstVal")

        println("Waiting for 2nd val")
        val mSecondVal = getSecondDiffered.await()
        println("Received 2nd val: $mSecondVal")

        println("Sum = ${ mFirstVal + mSecondVal}")
    }
}
suspend fun getFirstVal():Int{
    delay(1000)
    val firstVal = Random.nextInt(100)
    println("Returning first value is : $firstVal")
    return firstVal
}

suspend fun getSecondVal():Int{
    delay(1500)
    val secondVal = Random.nextInt(100)
    println("Returning second value is: $secondVal")
    return secondVal
}