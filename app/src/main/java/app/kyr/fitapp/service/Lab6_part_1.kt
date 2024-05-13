package app.kyr.fitapp.service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun printMessage(message: String, delayMillis: Long) {
    delay(delayMillis)
    println(message)
}

fun launchCoroutines() {
    println("Launching coroutines using launch:")
    runBlocking {
        val job1 = launch {
            printMessage("Coroutine 1", 1000)
        }
        val job2 = launch {
            printMessage("Coroutine 2", 1500)
        }
        job1.join()
        job2.join()
    }
}

fun asyncCoroutines() {
    println("Launching coroutines using async:")
    runBlocking {
        val deferred1 = async {
            printMessage("Coroutine 1", 1000)
            "Coroutine 1 finished"
        }
        val deferred2 = async {
            printMessage("Coroutine 2", 1500)
            "Coroutine 2 finished"
        }
        println(deferred1.await())
        println(deferred2.await())
    }
}

fun parallelDecomposition() {
    println("Parallel decomposition:")
    runBlocking {
        val result = coroutineScope {
            val deferred1 = async {
                printMessage("Task 1", 1000)
                "Task 1 result"
            }
            val deferred2 = async {
                printMessage("Task 2", 1500)
                "Task 2 result"
            }

            try {
                val result1 = deferred1.await()
                val result2 = deferred2.await()
                "$result1\n$result2"
            } catch (e: Exception) {
                "Exception occurred: ${e.message}"
            }
        }
        println(result)
    }
}

fun main() {
    launchCoroutines()
    println()
    asyncCoroutines()
    println()
    parallelDecomposition()
}