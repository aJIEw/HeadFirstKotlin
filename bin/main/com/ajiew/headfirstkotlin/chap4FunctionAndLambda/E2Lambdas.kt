package com.ajiew.headfirstkotlin.chap4FunctionAndLambda

/**
 * Lambda 表达式
 * https://www.kotlincn.net/docs/reference/lambdas.html
 * */

/**
 * lambda 表达式总是括在花括号中， 完整语法形式的参数声明放在花括号内，并有可选的类型标注， 函数体跟在一个 -> 符号之后。
 * 如果推断出的该 lambda 的返回类型不是 Unit，那么该 lambda 主体中的最后一个（或可能是单个）表达式会视为返回值。
 * */
val sum = { x: Int, y: Int -> x + y }

/**
 * 完整的 Lambda 表达式
 * */
val fullSum: (Int, Int) -> Int = { x, y -> x + y }

/**
 * it：单个参数的隐式名称
 * 如果编译器自己可以识别出签名，也可以不用声明唯一的参数并忽略 ->。该参数会隐式声明为 it：
 * */
fun showIt() {
    val ints = arrayOf(-1, 2, 3)
    var result = ints.filter { it > 0 }
    println(result)
}

fun main() {
    showIt()
}