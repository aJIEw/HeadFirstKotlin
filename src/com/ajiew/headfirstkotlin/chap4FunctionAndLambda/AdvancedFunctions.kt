package com.ajiew.headfirstkotlin.chap4FunctionAndLambda

/**
 * 高阶函数
 * https://www.kotlincn.net/docs/reference/lambdas.html
 * */

/**
 * 高阶函数是将函数用作参数或返回值的函数
 * */
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R,
    others: String = "default"
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

/**
 * 函数类型
 * Kotlin 使用类似 (Int) -> String 的一系列函数类型来处理函数的声明，
 * 这些类型具有与函数签名相对应的特殊表示法，即它们的参数和返回值:
 * - 所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型：(A, B) -> C
 * - 函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定：类型 A.(B) -> C
 * - 挂起函数属于特殊种类的函数类型，它的表示法中有一个 suspend 修饰符，例如 suspend () -> Unit 或者 suspend A.(B) -> C
 * - 还可以通过使用类型别名给函数类型起一个别称
 * */
typealias clickHandler = (name: String, event: String) -> Unit

/**
 * 函数类型实例化
 * 1. 使用函数字面值的代码块
 * - lambda 表达式: { a, b -> a + b }
 * - 匿名函数: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
 *
 * 2. 使用已有声明的可调用引用
 * - 顶层、局部、成员、扩展函数：::isOdd、 String::toInt
 * - 顶层、成员、扩展属性：List<Int>::size
 * - 构造函数：::Regex
 *
 * 3. 使用实现函数类型接口的自定义类的实例
 * */
class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}

val intFunction: (Int) -> Int = IntTransformer()

fun main() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas 表达式是花括号括起来的代码块
    items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
            acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    }, "void")

    // lambda 表达式的参数类型是可选的，如果能够推断出来的话
    // 如果最后一个参数是 Lambda 表达式则可以把方法写在圆括号外面
    val joinedToString = items.fold("Elements:") { acc, i -> "$acc $i" }

    // 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)

    println("joinedToString = $joinedToString")
    println("product = $product")

    val stringPlus: (String, String) -> String = String::plus
}