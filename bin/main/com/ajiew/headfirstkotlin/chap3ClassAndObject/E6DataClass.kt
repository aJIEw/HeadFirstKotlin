package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 数据类
 * https://www.kotlincn.net/docs/reference/data-classes.html
 * */

/**
 * 编译器自动从主构造函数中声明的所有属性导出以下成员：
 * - equals()/hashCode()
 * - toString() 格式是 "User(name=John, age=42)"
 * - componentN() 函数 按声明顺序对应于所有属性
 * - copy() 函数
 * */
data class Member(val name: String, val age: Int)

fun main() {
    val m = Member("Aaron", 25)
    println(m)

    println("Member name is ${m.name} and age is ${m.age}")

    println("componentN---------------")
    println(m.component1()) // name
    println(m.component2()) // age

    // 数据类的拷贝
    val copied = m.copy()
    println(copied)

    // 数据类的解构赋值
    val (name, age) = m
    println("name = $name, age = $age")

    // Pair and Triple, Kotlin 默认封装可用的数据类
    val pair = Pair("Aaron", 25)
    println("Using pair: name is ${pair.first}, age is ${pair.second}")

    val triple = Triple("Name", "Male", 25)
}