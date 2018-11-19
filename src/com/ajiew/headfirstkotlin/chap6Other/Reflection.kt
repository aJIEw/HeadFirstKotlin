package com.ajiew.headfirstkotlin.chap6Other

import com.ajiew.headfirstkotlin.chap3ClassAndObject.MyClass
import kotlin.reflect.KClass

/**
 * 反射
 * https://www.kotlincn.net/docs/reference/reflection.html
 * */
fun showReflection() {
    val c = MyClass::class

    println(c is KClass)

    println(c.visibility)

    // 要获得 Java 类引用，使用 .java 属性
    println(c.java.name)

    val clazzRef = c::class.qualifiedName
    println(clazzRef)
}

fun main() {
    showReflection()
}