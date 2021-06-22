package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        println("Receiving prime numbers")
        sendPrimes().collect {
            println("Received prime number $it")
        }
        println("Finished receiving prime numbers")

        println("Started getting numbers from  1..10")
        sendNumbers().collect {
            println("Received number $it")
        }
        println("Finished receiving numbers")

        println("Started getting numbers using asFlow")
        sendListAsFlow().collect {
            println("Received number $it")
        }
        println("Finished receiving numbers using asFlow")


        println("Started getting elements using FlowOf")
        sendElementsFlowOf().collect {
            println("Received element $it")
        }
        println("Finished receiving elements using FlowOf")

        val receiveNumberTimeLimit = sendNumbersTimeLimit()
        println("Flow hasn't stared yet")
        println("Flow Started")
        withTimeoutOrNull(1000) {
            receiveNumberTimeLimit.collect {
                println("Time limit number received: $it")
            }
        }
        println("Flow canceled with time limit withTimeoutOrNull ")

        println("Map operator")
        mapOperator()
        println("filter operator")
        filterOperator()
        println("Transform operator")
        transformOperator()
        println("Take operator")
        takeOperator()
        println("Reduce operator")
        reduceOperator()
        println("flowOn specific Dispatcher operator")
        flowOnOperator()

        println("Buffer operator")
        val time: Long = measureTimeMillis {
            generateBuffer().collect {
                delay(300)
                println("With delay and process time $it")
            }
        }
        println("Without buffer process time: $time")

        val time2: Long = measureTimeMillis {
            generateBuffer().buffer().collect {
                delay(300)
                println("With delay and process time $it")
            }
        }
        println("With buffer process time: $time2")

        println("Composing flow with .ZIP")
        zipComposingFlow()

        println("combine flows to map with latest records")
        combineFlows()

        println("Exception handling")
        tryCatchException()
        catchException()
        onCompletionException()
    }
}

fun sendPrimes(): Flow<Int> = flow {
    val primeList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primeList.forEach {
        kotlinx.coroutines.delay(it * 10L)
        emit(it)
    }
}

fun sendNumbers() = flow {
    for (i in 1..10)
        emit(i)
}

fun sendListAsFlow() = listOf(10, 20, 30, 40, 50).asFlow()

fun sendElementsFlowOf() = flowOf("J", "Jc", "Jay", "Jayachandra")

fun sendNumbersTimeLimit() = flow {
    listOf(1, 2, 3, 4).forEach {
        kotlinx.coroutines.delay(400)
        emit(it)
    }
}

suspend fun mapOperator() {
    (1..10).asFlow().map {
        delay(100L)
        "Mapping $it"
    }.collect {
        println(it)
    }
}

suspend fun filterOperator() {
    (1..10).asFlow().filter {
        it % 2 == 0
    }.collect {
        println("Filtered even numbers: $it")
    }
}

suspend fun transformOperator() {
    (1..10).asFlow().transform {
        emit("Emit any value my Name: Jc :$it")
        emit(it)
    }.collect { println("Transform operator: $it") }
}

suspend fun takeOperator() {
    (1..10).asFlow().take(6).collect { println("Take 1..10: $it") }
}

suspend fun reduceOperator() {
    val size = 5
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }
    println("Factorial of $size : $factorial")
}

suspend fun flowOnOperator() {
    (1..10).asFlow().flowOn(Dispatchers.IO).take(5).collect { println("flowOn Dispatcher.IO: $it") }
}

suspend fun generateBuffer() = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

suspend fun zipComposingFlow() {
    val village = flowOf("Konnali", "Kanaparthi", "Pudi")
    val studied = flowOf("5th", "7th", "10th")
    village.zip(studied) { a, b -> "I have studied '$b' class in '$a' village" }
        .collect { println(it) }
}

suspend fun combineFlows() {
    val numbers = flowOf(1, 2, 3, 4, 5).onEach { delay(300) }
    val letters = flowOf("A", "B", "C", "D", "E").onEach { delay(400) }
    numbers.combine(letters) { a, b -> "$a -> $b" }.collect { println(it) }
}

suspend fun tryCatchException() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .collect { println("try catch exception: $it") }
    } catch (e: Exception) {
        println("Caught exception: $e")
    }
}

suspend fun catchException() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .catch { e -> println("Caught exception: $e") }
            .collect { println("try catch exception: $it") }
    } catch (e: Exception) {
        println("Caught exception: $e")
    }
}

suspend fun onCompletionException() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .onCompletion { e ->
                if (e != null) {
                    println("Flow completed with Exception: $e")
                } else {
                    println("Flow completed successfully ")
                }
            }
            .catch { e -> println("Caught exception: $e") }
            .collect { println("try catch exception: $it") }
    } catch (e: Exception) {
        println("Caught exception: $e")
    }
}
