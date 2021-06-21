package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        repeat(100){
            launch {
                print("J")
            }
        }
    }
}