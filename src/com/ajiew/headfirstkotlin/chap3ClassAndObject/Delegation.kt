package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 委托
 * https://www.kotlincn.net/docs/reference/delegation.html
 * */

/**
 * Kotlin 内建对委托模式的支持
 * */
interface Base {
    val message: String

    fun print()
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base {
    override val message = "Message of BaseImpl"

    /**
     * 无法访问到 Derived 中的 message
     * */
    override fun print() { println(message) }

    override fun printMessage() {
        print(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}

/**
 * Derived 的超类型列表中的 by 子句表示 b 将会在 Derived 中内部存储，并且编译器将生成转发给 b 的所有 Base 的方法。
 * */
class Derived(b: Base) : Base by b {

    override val message = "Message of Derived"

    /**
     * 会覆盖由委托实现的接口成员
     * */
    override fun printMessage() {
        print("abc")
    }
}

fun main() {
    val b = BaseImpl(10)
    Derived(b).printMessage() // 打印的是 abc 而不是 10
    Derived(b).printMessageLine() // 打印 10 并换行

    println(Derived(b).message) // Message of Derived
    Derived(b).print() // Message of BaseImpl
}

