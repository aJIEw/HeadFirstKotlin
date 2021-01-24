package com.ajiew.headfirstkotlin.chap1Start

import com.ajiew.headfirstkotlin.chap3ClassAndObject.Whatever

/**
 * 基础语法
 * https://www.kotlincn.net/docs/reference/basic-syntax.html
 *
 * 全部语法
 * https://www.kotlincn.net/docs/reference/grammar.html
 *
 * 关键字与操作符
 * https://www.kotlincn.net/docs/reference/keyword-reference.html
 * */

fun out() {
    println("out method")
}

object BasicSyntax {

    /**
     * 常量
     * */
    val PI = 3.14

    /**
     * 变量
     * */
    var age = 24

    /**
     * this is documentation comment
     * */
    @JvmStatic
    fun main(args: Array<String>) {
    // 以上等同于
    //fun main(vararg args: String) {
    // vararg means variable arguments 即 Java 中的可变参数列表

        out()

        // this is line comment
        val str = "123456"

        val nullable: Int?
//        println(nullable) // 无法打印，因为未被初始化
        nullable = 666
        println(nullable)

        /* this is block commend */
        println("Hello World!")

        // 声明变量后用 :Type 表示变量类型，由于类型推断也可以省略
        var age: Int = 24
        println(age)

        // 在 Kotlin 中，if是一个表达式，它会返回一个值
        val old = if (age > 80) "old" else "not old"

        val x = parseInt("1")
        val y = parseInt("2")

//        val z = x * y // Warning is not allowed on a nullable receiver

        if (x != null && y != null) {
            // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
            println(x * y)
        }

        useLiteral()

        val des = useWhen(1)
        println(des)

        useRange(29)

        useStep()

        useDownto()

        useCollection()

        useDataClass()

        val w = Whatever()
        println(w.half)
    }

    /**
     * 方法声明，类型在 : 后面，返回值在方法参数后面
     * */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /* no return value (Unit is like void in Java) */
    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    /* return a nullable value */
    fun parseInt(str: String): Int? {
        return 1
    }

    /* 字符串模板 */
    fun useLiteral() {
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"

        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("$a", "b")}, but now is $a"
        println(s2)
        // 只有这样才能替换成功，说明不支持动态替换？
        println(s1.replace("1", "b"))
    }

    fun useIs(obj: Any): Int? {
        if (obj is String) {
            // `obj` 在该条件分支内自动转换成 `String`
            return obj.length
        }

        // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
        return null
    }

    fun useWhen(obj: Any): String = when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

    fun useRange(x: Int) {
        if (x in 24..35)
            println("Young")
        else
            println("Unknown")
    }

    fun useStep() {
        for (i in 2..9 step 2) {
            if (i % 2 == 0)
                println("$i is an even number")
        }
    }

    fun useDownto() {
        for (i in 99 downTo 90 step 2) {
            if (i % 2 != 0)
                println("$i is an odd number")
        }
    }

    fun useCollection() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    }

    fun useDataClass() {
        val customer = Customer("aJIEw", "ajiew42@gmail.com")
        println("Customer: name = ${customer.name}, email = ${customer.email}")
    }
}