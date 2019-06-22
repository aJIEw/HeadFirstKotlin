package com.ajiew.headfirstkotlin.chap6Other

/**
 * 相等性
 * https://www.kotlincn.net/docs/reference/equality.html
 * */

/**
 * Kotlin 中有两种类型的相等性
 * - 结构相等，用 equals() 检查
 * - 引用相等，两个引用指向同一对象
 * */
fun showStructureEqual() {
    val a = "a"
    val b = "a"

    val result = a == b
    println(result)
}

fun showReferenceEqual() {
    val a = "a"
    val b = "b"

    val result = a === b
    println(result)
}

fun main() {
    print("结构相等：")
    showStructureEqual()

    print("引用相等：")
    showReferenceEqual()
}
