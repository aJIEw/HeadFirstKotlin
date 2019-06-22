package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 内联函数
 * https://www.kotlincn.net/docs/reference/inline-classes.html
 * */

/**
 * 内联类的主要用途是包装某些数据，比如一些基本数据类型，使用内联类可以比使用一般的数据类效率更高
 * */
inline class Password(val value: String)

/**
 * 内联类等局限：
 * - 不能有 init 代码块
 * - 不能有内部类
 * - 不能有幕后属性
 * */
object InlineClass {

    @JvmStatic
    fun main(args: Array<String>) {
        val securePassword = Password("Don't try this in production")
        println(securePassword)

        println(securePassword.value)

        /* 内联类会被编译成它所包含的类型 */
        println(securePassword.javaClass.name) // String
    }
}