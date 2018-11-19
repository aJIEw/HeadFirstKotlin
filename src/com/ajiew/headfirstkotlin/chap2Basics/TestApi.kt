package com.ajiew.headfirstkotlin.chap2Basics

/**
 * 测试一些基本 API 的使用
 * 标准库主页 https://kotlinlang.org/api/latest/jvm/stdlib/index.html
 * 核心方法与类型 https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/index.html
 * 集合 https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html
 * Math https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/index.html
 * IO https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/index.html
 * 多线程 https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.concurrent/index.html
 * 协程 https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/index.html
 * */
fun main() {
    val map = mapOf(
        "name" to "Aaron Chen",
        "age" to 23
    )

    val mutableMap = mutableMapOf(
        "name" to "Kevin Chen",
        "age" to 24
    )
    println(map)
}