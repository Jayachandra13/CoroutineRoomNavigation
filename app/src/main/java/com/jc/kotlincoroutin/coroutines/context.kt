package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        launch(CoroutineName("jcCoroutine")) {
            println("From coroutine name: ${coroutineContext[CoroutineName.Key]}")
        }
    }
}