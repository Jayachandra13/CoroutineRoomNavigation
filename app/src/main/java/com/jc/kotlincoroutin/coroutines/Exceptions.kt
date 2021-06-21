package com.jc.kotlincoroutin.coroutines

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException

fun main(){
    runBlocking {
        val myExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception Details: ${throwable.localizedMessage}") }
        val job = GlobalScope.launch(myExceptionHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("Exception_A")
        }
        job.join()

        val differed = GlobalScope.async {
            println("throwing exception from async")
            throw ArithmeticException("Math Exception")
        }
        try {
            differed.await()
        } catch (e: ArithmeticException){
            println("Caught ArithmeticException in await(): ${e.localizedMessage}")
        }

    }
}