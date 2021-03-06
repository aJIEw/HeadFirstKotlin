package com.ajiew.headfirstkotlin.chap1Start

import com.ajiew.headfirstkotlin.chap3ClassAndObject.MyClass
import java.io.File
import kotlin.random.Random

/**
 * 习惯用法
 * https://www.kotlincn.net/docs/reference/idioms.html
 * 作用域函数
 * https://www.kotlincn.net/docs/reference/scope-functions.html
 * */

/**
 * 演示一些 Kotlin 中的习惯用法
 * */
fun main() {
    println("--------------use data class--------------")
    val cus = Customer("aJIEw", "ajiew42@gmail.com")
    println(cus)

    useMap()

    println(useLazy())

    ifNotNull()

    toAndToList()

    /**
     * 下面的方法中：
     * - let with run 都是返回 lambda 表达式结果，其中 let 接受 it 作为参数，其它都是对象本身 this，比较特殊的是 with 不是扩展函数，而是将对象作为参数传入
     * - apply also take 都是返回操作的对象本身，其中 apply 将本身作为参数 this，其它使用 it 作为参数
     * */
    let()

    with()

    run()

    apply()

    also()

    take()
}

var cat = Customer("Cat", "cat@cat.com")

fun useMap() {
    println("--------------use mapOf--------------")
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    mapOf(1 to "c")
    println("map[c] = " + map["c"])
}

/* 延迟属性 */
fun useLazy(): Any {
    println("--------------use lazy--------------")
    val p: String by lazy {
        println("doingCalculation")

        val cal = "Got Result"
        /**
         * lazy 时最后的值是返回值
         * */
        cal
    }
    return p
}

/* 判断不为空的几种方式 */
fun ifNotNull() {
    println("--------------use not null--------------")
    val files = File(System.getProperty("user.dir")).listFiles()

    // if not null then execute ?.
    println("folders number = " + files?.size)

    // if null and else ?:
    val empty = files ?: true
    if (empty is Boolean && empty) {
        println("directory is empty")
        return
    }

    // if null then 写法 ?:
    val firstFile = files.firstOrNull() ?: "first is null"
    println("First folder's name is: $firstFile")

    // if not null execute code ?.let
    files?.let {
        println("folders number: " + files.size)
    }
}

fun toAndToList() {
    println("--------------to and toList--------------")
    val myClass = MyClass()
    val pair = cat to myClass
    println("first is ${pair.first}")
    val toList = pair.toList()
    println("size = " + toList.size)
    println(toList[0])
    println(toList[1])
}

/**
 * The context object is available as an argument (it).
 * The return value is the lambda result.
 * */
fun let() {
    println("--------------let--------------")
    val result = cat.let {
        println("do something in let and return a result")
        println("use name: ${it.name}")

        // return value
        it.name
    }
    println(result)
}

/**
 * non-extension function: the context object is passed as an argument,
 * but inside the lambda, it's available as a receiver (this).
 * The return value is the lambda result.
 * */
fun with() {
    println("--------------with--------------")
    val numbers = mutableListOf("one", "two", "three")
    val result = with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
        add("four") // last statement or value as return value
//        this // if you return this then it's like apply
    }
    println("result = $result")
    println(numbers)
}

/**
 * The context object is available as a receiver (this).
 * Just like let, but it can run anything.
 * The return value is the lambda result.
 * */
fun run() {
    println("--------------run and runCatching--------------")
    val result = cat.run {
        println("do something in run, customer name is ${name}")
        1
    }
    println(result)

    val result2 = cat.runCatching {
        println("do something in runCatching, customer name is $name")
        name + email
    }
    println("Result is $result2")

    val what = run {
        println("do something in run")
        "result"
    }
    println(what)
}

/**
 * The context object is available as a receiver (this).
 * The return value is the object itself.
 * */
fun apply() {
    println("--------------apply--------------")
    val rect = Customer("Aaron", "aaron@ajiew.me")
    println("before rect = $rect")

    val result = rect.apply {
        println("do something in apply and return value")
        println("you can use the value directly, $rect")
        // 对于一些构造函数参数较多的对象创建比较实用
        name = "aJIEw"
        email = "ajiew@ajiew.me"
    }
    println("result = $result")
    println("after apply rect = $rect")
}

/**
 * The context object is available as an argument (it).
 * The return value is the object itself.
 * */
fun also() {
    println("--------------also--------------")
    val numbers = mutableListOf("one", "two", "three")
    numbers
        .also {
            println("do something in also and return value")
            println("you have to use \"it\", ${it}")
        }.also { println("The list elements before adding new one: $it") }
        .add("four")
    println(numbers)
}

/**
 * The context object is available as an argument (it).
 * */
fun take() {
    println("--------------takeIf and takeUnless--------------")
    val number = Random.nextInt(100)
    val evenOrNull = number.takeIf {
        // return the object it matches the predicate. Otherwise, it returns null.
        it % 2 == 0
    }
    val oddOrNull = number.takeUnless {
        // return the object it doesn't match the predicate. Otherwise, it returns null.
        it % 2 == 0
    }
    println("even: $evenOrNull, odd: $oddOrNull")
}
