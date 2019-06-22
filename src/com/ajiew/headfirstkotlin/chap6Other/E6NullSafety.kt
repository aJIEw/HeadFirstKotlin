package com.ajiew.headfirstkotlin.chap6Other


/**
 * 空安全 ?. and ?:
 * https://www.kotlincn.net/docs/reference/null-safety.html
 * */

/**
 * 可空类型与非空类型
 * */
fun nullSafety() {
    var a: String = "abc"
//    a = null // 编译错误，一个非空字符串不允许设为空

    /**
     * 如果要允许为空，我们可以声明一个变量为可空字符串
     * */
    var b: String? = "abc"
    b = null // ok
    println(b)

    /**
     * 如果你想访问 b 的同一个属性，那么这是不安全的，并且编译器会报告一个错误
     * */
//    val l = b.length // 变量 b 可能为空
}

/**
 * 在条件中检查 null
 * */
fun checkNull() {
    val c = "Kotlin"
    if (c != null && c.length > 0) {
        println("String of length ${c.length}")
    } else {
        println("Empty string")
    }
}

/**
 * 安全的调用
 * 安全调用操作符，写作 ?.
 * */
fun safeCall() {
    val a = "Kotlin"
    println(a?.length)

    val b: String? = null // 如果 b 非空，就返回 b.length，否则返回 null，这个表达式的类型是 Int?
    println(b?.length) // 不用担心 null pointer exception 了

    /**
     * 安全调用在链式调用中很有用
     * */
    // bob?.department?.head?.name // 如果任意一个属性（环节）为空，这个链式调用就会返回 null
}

/**
 * 与 let 一起使用
 * */
fun safeCallWithLet() {
    val listWithNulls: List<String?> = listOf("Kotlin", null)
    for (item in listWithNulls) {
        item?.let { println(it) } // 输出 A 并忽略 null
    }
}

/**
 * Elvis 操作符 ?:
 * 当我们有一个可空的引用 r 时，我们可以说“如果 r 非空，我使用它；否则使用某个非空的值 x”
 * */
fun usingElvis(b: String?) {
    val l = b?.length ?: -1
    println("length is $l")
}

/**
 * !! 操作符 (不要用！)
 * 非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异常
 * */
fun throwNPE(b: String?) {
    val l = b!!.length
    println("Let's Throw NPE!")
}

/**
 * 安全的类型转换
 * 如果对象不是目标类型，那么常规类型转换可能会导致 ClassCastException。
 * 另一个选择是使用安全的类型转换，如果尝试转换不成功则返回 null
 * */
fun castSafe(a: Any) {
    val aInt: Int? = a as? Int
    if (aInt != null) println("$aInt was casted to Int")
    else println("cast failed")
}

/**
 * 可空类型的集合
 * 如果你有一个可空类型元素的集合，并且想要过滤非空元素，你可以使用 filterNotNull 来实现
 * */
fun testFilterNotNull() {
    val nullableList: List<Int?> = listOf(1, 2, null, 4)
    val intList: List<Int> = nullableList.filterNotNull()
    println("not null list: $intList")
}

fun main() {
    nullSafety()

    checkNull()

    safeCall()

    safeCallWithLet()

    usingElvis("what")
    usingElvis(null)

//    throwNPE(null)

    castSafe("11")

    testFilterNotNull()
}