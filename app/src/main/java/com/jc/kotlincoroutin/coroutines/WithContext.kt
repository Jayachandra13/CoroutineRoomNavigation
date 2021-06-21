package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        launch(Dispatchers.Default) {
            println("First context: $coroutineContext")
            launch(Dispatchers.IO) {
                println("Second context: $coroutineContext")
            }
            println("Third context: $coroutineContext")
        }
    }
}