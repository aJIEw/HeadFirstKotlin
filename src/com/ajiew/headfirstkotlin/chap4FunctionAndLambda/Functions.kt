package com.ajiew.headfirstkotlin.chap4FunctionAndLambda


/**
 * 函数
 * https://www.kotlincn.net/docs/reference/functions.html
 * */

/**
 * 一个简单的函数。形式如下：
 * fun 方法名(参数名:参数类型): 返回值类型 { 方法体 }
 * */
fun double(x: Int): Int {
    return 2 * x
}

/**
 * 函数参数可以有默认值
 * - 覆盖方法总是使用与基类型方法相同的默认参数值
 * - 如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用命名参数调用该函数来使用
 * */
fun read(b: Array<Byte>, start: Int = 0, len: Int = b.size - 1) {
    for (i in start..len) {
        print("${b[i]}, ")
    }
    println()
}

/**
 * 如果最后一个 lambda 表达式参数从括号外传给函数函数调用，那么允许默认参数不传值
 * */
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
    println("start of foo")
    println("bar = $bar, baz = $baz")
    qux()
    println("end of foo")
}

/**
 * 可变参数
 * */
fun printVararg(vararg strings: String) {
    for (string in strings) {
        print("$string, ")
    }
    println()
}

/**
 * 返回 Unit 的函数
 * 除此之外，所以函数必需显式指明返回类型
 * */
fun returnNothing(name: String?): Unit {
    if (name != null)
        println("Hello ${name}")
    else
        println("Hi there!")
}

/**
 * 单表达式函数
 * 当函数返回单个表达式时，可以省略花括号并且在 = 符号之后指定代码体即可
 * */
fun double2(x: Int): Int = x * 2

/**
 * 中缀表示法
 * 标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。
 * 中缀函数必须满足以下要求：
 * - 它们必须是成员函数或扩展函数；
 * - 它们必须只有一个参数；
 * - 其参数不得接受可变数量的参数且不能有默认值。
 * */
infix fun Long.shl(x: Long): Int {
    println("shl $x")
    return 0
}

/**
 * 函数作用域
 * 在 Kotlin 中函数可以在文件顶层声明，也可以声明在局部作用域、作为成员函数以及扩展函数
 * */
fun demoLocalFun(name: String) {
    val sameSex = false
    // Kotlin 支持局部函数
    fun filter(age: Int) {
        // 局部函数可以访问外部函数（即闭包）的局部变量 sameSex
        if (age < 18 || sameSex) return
        else {
            println("Hi, $name, let's do something dirty:P")
        }
    }
}


class SomeClass() {

    /**
     * 成员函数，在类或对象内部定义的函数
     * */
    fun memberFun() {
        println("this is a member function")
    }
}

/**
 * 泛型函数
 * */
fun <T> singletonList(item: T): List<T> {
    return arrayListOf()
}

/**
 * 内联函数
 * */

/**
 * 扩展函数
 * */

/**
 * 高阶函数和 Lambda 表达式
 * */

/**
 * 尾递归函数
 * Kotlin 支持一种称为尾递归的函数式编程风格。这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。
 * - 用 tailrec 修饰符标记尾递归函数，要符合 tailrec 修饰符的条件的话，函数必须将其自身调用作为它执行的最后一个操作。
 * - 在递归调用后有更多代码时，不能使用尾递归，并且不能用在 try/catch/finally 块中。
 * - 目前尾部递归只在 JVM 后端中支持。
 * */
val eps = 1E-10 // "good enough", could be 10^-15

/**
 * 计算余弦的不动点（fixpoint of cosine）
 * */
tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (Math.abs(x - Math.cos(x)) < eps) x
    else findFixPoint(Math.cos(x))


fun main() {
    var result = double(11)
    println(result)

    val bArray = arrayOf('a'.toByte(), 'b'.toByte(), 'c'.toByte())
    read(bArray) // 可以省略有了默认参数的参数
    read(bArray, 1, 2)

    // 直接在调用的时候写最后一个函数的函数体就可以
    foo { println("do something") }

    // 命名参数：可以在调用函数时使用命名的函数参数，有利于增强代码的可读性
    foo(bar = 22, baz = 33) { println("do nothing") } // 注意：如果使用了命名参数，则只有第一个命名参数可省略

    printVararg("Aaron", "is", "very", "cute")
    // 也可使用星号操作符（即扩展表达式）将可变数量参数（vararg）以命名形式传入
    printVararg(strings = *arrayOf("a", "b", "c"))
    printVararg("x", "y", "z")

    // 可变参数列表支持使用扩展表达式，数组前加 *
    val arr = arrayOf("some", "content")
    printVararg(*arr, "and", "more")

    returnNothing(null)

    var inflix: Int = 1L shl 5L
    // 等同于这样
    1.shl(2)
    println(inflix)

    // 调用成员函数
    SomeClass().memberFun()

    println(findFixPoint(22.2))
}