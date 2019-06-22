package com.ajiew.headfirstkotlin.chap6Other

import com.ajiew.headfirstkotlin.chap1Start.BasicSyntax.parseInt

/**
 * 异常
 * https://www.kotlincn.net/docs/reference/exceptions.html
 * */

/**
 * Kotlin 中所有异常类都是 Throwable 类的子类
 * */
fun throwException() {
    throw Exception("Hi There!")
}

/**
 * 使用 try-表达式来捕获异常
 * */
fun usingTryCatch() {
    try {
        throwException()
    } catch (e: Exception) {
        println(e.toString())
//        e.printStackTrace()
    } finally {
        println("I'm done")
    }
}

/**
 * Try 是一个表达式
 * 返回值是 try 块中的最后一个表达式或者是（所有）catch 块中的最后一个表达式
 * */
fun tryAsExpression(input: String) {
    val a: Int? = try {
        parseInt(input) // 如果没抛异常则返回值就是 parseInt 的合法返回值
    } catch (e: NumberFormatException) {
        null
    }
    println("the result is $a")
}

/**
 * Kotlin 没有受检的异常
 * */

/**
 * Nothing 类型
 * 在 Kotlin 中 throw 是表达式，所以你可以使用它（比如）作为 Elvis 表达式的一部分
 * throw 表达式的类型是特殊类型 Nothing，该类型没有值，而是用于标记永远不能达到的代码位置
 * */
fun throwReturnNothing(person: NullablePerson) {
    val s = person.name ?: fail("Name Required")
    println(s) // 在此已知“s”已初始化
}

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun typeInfer() {
    val x = null           // “x”具有类型 `Nothing?`
    if (x is Nothing?) println("x type is Nothing?")
    val l = listOf(null)   // “l”具有类型 `List<Nothing?>
}


fun main() {
//    throwException()

    usingTryCatch()

    tryAsExpression("1")

//    throwReturnNothing(NullablePerson(null))

    typeInfer()
}

class NullablePerson(val name: String?)