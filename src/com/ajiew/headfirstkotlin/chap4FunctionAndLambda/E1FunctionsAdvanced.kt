package com.ajiew.headfirstkotlin.chap4FunctionAndLambda

/**
 * Kotlin 中的 function 是 first-class 的，限制较少，和 JS 类似，比如支持将函数当做变量或者参数传递等等。
 * https://www.kotlincn.net/docs/reference/lambdas.html
 * */

/**
 * 高阶函数是将函数用作参数或返回值的函数
 * 比如下面 fold 函数的第二个参数接收的就是一个函数，因此它是一个高阶函数
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
 * <bold>关于高阶函数的函数类型</bold>
 * Kotlin 使用类似 (Int) -> String 的一系列函数类型（参数类型 -> 返回值类型）来处理函数的声明，
 * 这些类型具有与函数签名相对应的特殊表示法，即它们的参数和返回值:
 * - 所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型：(A, B) -> C
 * - 函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定：类型 A.(B) -> C
 * - 挂起函数属于特殊种类的函数类型，它的表示法中有一个 suspend 修饰符，例如 suspend () -> Unit 或者 suspend A.(B) -> C
 * - 还可以通过使用类型别名给函数类型起一个别称，比如下面这样
 * */
typealias clickHandler = (name: String, event: String) -> Unit

/**
 * 函数类型实例化
 * 1. 使用函数字面值的代码块
 * 1.1 lambda 表达式: { a, b -> a + b }
 * 1.2 匿名函数: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
 *
 * 2. 使用已有声明的可调用引用
 * 2.1 顶层、局部、成员、扩展函数：::isOdd、 String::toInt
 * 2.2 顶层、成员、扩展属性：List<Int>::size
 * 2.3 构造函数：::Regex
 *
 * 3. 使用实现函数类型接口的自定义类的实例
 * */

// 1.1
val lambda = {
    println("do something here")
    "result"
}

fun specialFun(p1: Int, p2: Int, calculator: (a: Int, b: Int) -> Int): Int {
    println("Before calculation:")
    val result = calculator(p1, p2)
    println("After calculation:")
    return result
}

// 1.2
val anonymousMethod = fun(source: String): String { return "${source.capitalize()} - $source" }

// 2.1
val advancedCall = 47::toString

// 3
class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}

val intFunction: (Int) -> Int = IntTransformer()

fun main() {
    val items = listOf(1, 2, 3, 4, 5)

    println(lambda())
    println()

    val specialResult = specialFun(2, 40) { a, b ->
        println("calculating...")
        a + b
    }
    println("specialResult = $specialResult")
    println()

    // Lambdas 表达式
    items.fold(0, { acc: Int, i: Int -> // 1.1 如果一个 lambda 表达式有参数，前面是参数，后跟“->”

        // 1.2 下面是函数主体
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")

        // 1.3 lambda 表达式中的最后一个表达式是返回值
        result
    }, "void")
    println()

    // 2.1 lambda 表达式的参数类型是可选的，如果能够推断出来的话
    // 2.2 如果最后一个参数是 Lambda 表达式则可以把方法写在圆括号外面
    val joinedToString = items.fold("Elements:") { acc, i -> "$acc $i" }
    println("joinedToString = $joinedToString")
    println()

    val list = mutableListOf(1, 2, 3)
    // 2.3 lambda 函数中如果参数只有一个可以省略不写，并且使用 it 代替
    list.removeAll { it > 2 }
    println("list after remove: \n$list")
    println()

    // 3.1 函数引用
    // 这里定义了一个函数 stringPlus，它引用 String 的 plus 方法作为实现
    val stringPlus: (String, String) -> String = String::plus
    var stringPlusResult = stringPlus("1", "2")
    println("stringPlusResult: $stringPlusResult")
    println()

    // 3.2 函数引用也可以用于高阶函数的调用中
    val product = items.fold(1, Int::times)
    println("product = ${product}")
}