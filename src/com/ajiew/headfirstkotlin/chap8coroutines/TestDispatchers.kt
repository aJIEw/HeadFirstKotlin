package com.ajiew.headfirstkotlin.chap8coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    withContext(Dispatchers.Default) {
        launch() {
            println("Do something in ${Thread.currentThread().name}")
        }
    }

    launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // 父协程的上下文，主 runBlocking 协程
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }

    // main CoroutineScope 可以放到全局，便于统一管理
    var mainScope = MainScope()

    val scope = CoroutineScope(CoroutineName("Name") + Dispatchers.IO)
    val job = scope.launch(start = CoroutineStart.DEFAULT){
        println("hello world")
    }
}

// 这是你的第一个挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}