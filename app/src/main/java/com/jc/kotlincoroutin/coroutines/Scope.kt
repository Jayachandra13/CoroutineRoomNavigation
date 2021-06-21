package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.*

fun main(){
    println("Blocking program now")

    runBlocking {
        launch {
            delay(1000)
            println("From runBlocking ")
        }

        GlobalScope.launch {
            delay(500)
            println("From GlobalScope")
        }
        coroutineScope {
            launch {
                delay(1500)
                println("From coroutineScope")
            }
        }
    }

    println("Blocking ends")
}