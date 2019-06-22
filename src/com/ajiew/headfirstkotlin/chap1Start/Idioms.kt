package com.ajiew.headfirstkotlin.chap1Start

import com.ajiew.headfirstkotlin.chap3ClassAndObject.MyClass
import java.io.File

/**
 * 习惯用法
 * https://www.kotlincn.net/docs/reference/idioms.html
 * */

/**
 * 演示一些 Kotlin 中的习惯用法
 * */
fun main() {
    val cus = Customer("Aaron Chen", "kevin10tod@gmail.com")
    println(cus)

    println(useLazy())

    useMap()

    ifNotNull()

    apply()

    also()

    let()

    toAndToList()

    take()

    run()
}

var m = Customer("Cat", "cat@cat.com")

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
    val empty = files ?: true
    if (empty is Boolean && empty) {
        println("directory is empty")
        return
    }

    for (file in files) {
        println(file.absolutePath)
    }

    // if null then 写法 ?:
    val firstFile = files.firstOrNull() ?: "first is null"
    println("First folder's name is: $firstFile")

    // if not null execute code ?.let
    files?.let {
        println("folders number: " + files.size)
    }
}

fun toAndToList() {
    println("--------------to--------------")
    val myClass = MyClass()
    val pair = m to myClass
    val toList = pair.toList()
    println("size = " + toList.size)
    println(toList[0])
    println(toList[1])
}

/**
 * Calls the specified function block with this value as its receiver and returns this value.
 * */
fun apply() {
    println("--------------apply--------------")
    val result = m.apply {
        println("do something in apply and return value")
        println("you can use the value directly, $name")
    }
    println(result)
}

/**
 * Calls the specified function block with this value as its argument and returns this value.
 * */
fun also() {
    println("--------------also--------------")
    val result = m.also {
        println("do something in also and return value")
        println("you have to use \"it\", ${it.name}")
    }
    println(result)
}

fun let() {
    println("--------------to and toList--------------")
    val result = m.let {
        println("do something in let and return a result")
        println("use name: ${it.name}")
        it.name
    }
    println(result)
}

fun take() {
    println("--------------takeIf and toList--------------")
    val aaron = m.takeIf {
        m.name == "Aaron"
    } // only satisfy
    println(aaron)

    val notAaron = m.takeUnless {
        m.name == "Aaron"
    } // only only satisfy
    println(notAaron)
}

fun run() {
    println("--------------run and toList--------------")
    val result = m.run {
        println("do something in run, customer name is $name")
        1
    }
    println(result)

    val result2 = m.runCatching {
        println("do something in runCatching, customer name is $name")
        name + email
    }
    println(result2)
}
