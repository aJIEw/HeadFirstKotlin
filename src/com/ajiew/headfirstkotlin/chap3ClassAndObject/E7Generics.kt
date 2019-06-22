package com.ajiew.headfirstkotlin.chap3ClassAndObject

import com.sun.org.apache.bcel.internal.generic.NEW
import kotlin.random.Random

/**
 * 泛型
 * https://www.kotlincn.net/docs/reference/generics.html
 * */

/**
 * 声明处型变：使用 out 或 in 修饰符。我们可以标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。
 * 一般原则是：当一个类 Class 的类型参数 T 被声明为 out 时，它就只能出现在 Class 的成员的输出-位置，
 * 但回报是 Class<Base> 可以安全地作为 Class<Derived>的超类。你可以认为 Class 是 T 的生产者，而不是 T 的消费者。
 * */
interface Source<out T> {
    fun nextT(): T
}

class Origin : Source<String> {
    override fun nextT(): String {
        return "S" + Random(1).nextInt(100, 999)
    }
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
    println(objects.nextT())
}

/**
 * Kotlin 还有一个型变注释：in。它使得一个类型参数逆变：只可以被消费而不可以被生产。
 * */
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

object Generics {

    @JvmStatic
    fun main(args: Array<String>) {
        demo(Origin())
    }
}