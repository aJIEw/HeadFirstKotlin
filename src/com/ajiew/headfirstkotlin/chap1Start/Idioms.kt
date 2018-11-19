package com.ajiew.headfirstkotlin.chap1Start

import java.io.File

/**
 * 习惯用法
 * https://www.kotlincn.net/docs/reference/idioms.html
 * */

/**
 * 演示一些 Kotlin 中的习惯用法
 * */
fun main(args: Array<String>) {
    val cus1 = Customer("Aaron Chen", "kevin10tod@gmail.com")
    println(cus1)

    println(useLazy())

    useMap()

    ifNotNull()
}

fun useMap() {
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println("map[c] = " + map["c"])
}

/* 延迟属性 */
fun useLazy(): Any {
    val p: String by lazy {
        // 计算该字符串
        val cal = "doingCalculation"
        /**
         * lazy 时最后的值是返回值
         * */
        cal
    }
    return p
}

fun ifNotNull() {
    val files = File(System.getProperty("user.dir")).listFiles()

    // if not null then execute ?.
    println("folders number = " + files?.size)

    // if null and else ?:
    var empty = files ?: true
    if (empty is Boolean && empty) {
        println("directory is empty")
        return
    }

    for (file in files) {
        println(file.absolutePath)
    }

    var firstFile = files.firstOrNull() ?: "first is null"
    println("First folder's name is: $firstFile")

    // if not null execute code ?.let
    files?.let {
        println("folders number: " + files.size)
    }
}
