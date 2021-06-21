package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job1 = launch {
//            delay(3000)
            println("Job1 launched")

            val job2 = launch {
                delay(3000)
                println("Job2 launched")
            }
            job2.invokeOnCompletion { println("Jon2 completed") }

            val job3 = launch {
                delay(3000)
                println("Job3 launched")
            }
            job3.invokeOnCompletion { println("Job3 completed") }
        }

        job1.invokeOnCompletion { println("Job1 completed") }
        delay(500)
        println("Job 1 will cancel")
        job1.cancel()
    }
}