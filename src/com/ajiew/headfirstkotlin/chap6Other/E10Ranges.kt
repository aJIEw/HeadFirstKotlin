package com.ajiew.headfirstkotlin.chap6Other

/**
 * 区间
 * https://www.kotlincn.net/docs/reference/ranges.html
 * */

fun showRangeTo() {
    /**
     * 使用 rangeTo 函数
     * */
    for (i in 1..10) print("$i, ")
    println()

    /**
     * !in 对应于 in
     * */
    val myAge = 25
    if (myAge !in 1..16) {
        println("$myAge years old does not belong to a teenage group")
    }

    /**
     * 倒序迭代 downTo
     * */
    for (i in 10 downTo 1) print("$i, ")
    println()

    /**
     * 任意步长迭代 step
     * */
    for (i in 1..10 step 2) print("$i, ")
    println()

    for (i in 10 downTo 1 step 2) print("$i, ")
    println()

    /**
     * 不包括其结束元素 until
     * */
    // i in [1, 10) until 函数 排除了 10
    for (i in 1 until 10) print("$i, ")
}

fun main() {
    showRangeTo()
}