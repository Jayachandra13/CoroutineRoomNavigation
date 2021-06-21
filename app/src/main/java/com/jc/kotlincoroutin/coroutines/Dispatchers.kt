package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.*

fun main(){
    runBlocking {
        launch(Dispatchers.Default) {
            println("Default Dispatcher: Thread: ${Thread.currentThread().name}")
        }
//        launch(Dispatchers.Main) {
//            println("Main Dispatcher: Thread: ${Thread.currentThread().name}")
//        }
        launch(Dispatchers.IO) {
            println("IO Dispatcher: Thread: ${Thread.currentThread().name} ")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined Dispatcher 1: Thread: ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined Dispatcher 2: Thread: ${Thread.currentThread().name}")

            launch {
                delay(1000)
                println("Dispatcher not defined : Thread: ${Thread.currentThread().name}")
            }
        }
        launch(newSingleThreadContext("JcThread")) {
            println("newSingleThreadContext Thread: ${Thread.currentThread().name}")
        }
    }
}