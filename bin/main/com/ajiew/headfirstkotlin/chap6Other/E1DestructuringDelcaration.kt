package com.ajiew.headfirstkotlin.chap6Other


/**
 * 解构声明
 * https://www.kotlincn.net/docs/reference/multi-declarations.html
 * */

data class Person(val name: String, val age: Int)

val person = Person("Aaron", 24)

/**
 * 解构声明
 * */
fun showCase() {
    // 一个解构声明同时创建多个变量
    val (name, age) = person

    println("name = $name, age = $age")

    // 下划线用于未使用的变量
    val (n, _) = person
    println(n)

    /**
     * 数据类会为属性自动生成 componentN 函数，按照属性声明的顺序
     * */
    println(person.component1())
    println(person.component2())

    val strArr = mapOf("name" to "Aaron Chen", "age" to 24)
    /**
     * 使用解构声明的方式遍历 map
     * */
    for ((a, b) in strArr) {
        println("$a = $b")
    }

}

fun main() {
    showCase()
}