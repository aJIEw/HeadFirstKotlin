package com.ajiew.headfirstkotlin.chap7JavaInterop

import java.util.*


/**
 * 在 Kotlin 中调用 Java 代码
 * https://www.kotlincn.net/docs/reference/java-interop.html
 * */

/**
 * 复制 list 到 Java 的 ArrayList 中，相当于 Kotlin 的 MutableList
 * */
fun callList(source: List<String>) {
    val list = java.util.ArrayList<String>()
    // “for”-循环用于 Java 集合：
    for (item in source) {
        list.add(item)
    }
    // 操作符约定同样有效：
    for (i in 0 until source.size) {
        list[i] = source[i] // 调用 get 和 set
    }
}

/**
 *  Java 约定的 getter 和 setter 的方法在 kotlin 中以属性的形式进行访问
 * */
fun calendarDemo() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // 调用 getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY      // 调用ll setFirstDayOfWeek()
    }
    if (!calendar.isLenient) {                         // 调用 isLenient()
        calendar.isLenient = true                      // 调用 setLenient()
    }
}

/**
 * Java 标识符如果是 Kotlin 中的关键字则需要使用 `` 进行转义
 * */
fun callWithEscape() {
    val d = DummyJavaClass()
    d.`is`()
}

fun main() {
    callList(listOf("a", "b", "c"))

    calendarDemo()

    callWithEscape()
}