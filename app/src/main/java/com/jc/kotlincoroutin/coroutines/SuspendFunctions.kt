package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var functionCalls = 0

fun main(){
    GlobalScope.launch { firstFun() }
    GlobalScope.launch { secondFun() }
    Thread.sleep(1500)
    print("Number of times function called is: $functionCalls")
}

suspend fun firstFun(){
    delay(500)
    print("Hello, ")
    functionCalls++
}

suspend fun secondFun(){
    delay(1000)
    print("World!")
    functionCalls++
}