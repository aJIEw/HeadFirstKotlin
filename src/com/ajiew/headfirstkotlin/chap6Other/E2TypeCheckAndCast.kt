package com.ajiew.headfirstkotlin.chap6Other

/**
 * 类型检查与转换
 * https://www.kotlincn.net/docs/reference/typecasts.html
 * */

fun testIsAndNot() {
    val obj = "something unknown"

    if (obj is String) {
        println("$obj is string and length is ${obj.length}")
    }

    // or

    if (obj !is String) { // 与 !(obj is String) 相同
        println("Not a String")
    } else {
        println("length is " + obj.length)
    }
}

/**
 * 在许多情况下，不需要在 Kotlin 中使用显式转换操作符，
 * 因为编译器跟踪不可变值的 is-检查以及显式转换，并在需要时自动插入（安全的）转换
 * */
fun autoCasting(arg: Any) {


    /**
     * is 检查后，自动转换为 String，所以可以直接调用
     * */
    if (arg is String) println("String length is " + arg.length)
}

fun moreAutoCasting(x: Any) {

    /**
     * `||` 右侧的 x 自动转换为字符串，所以调用是安全的
     * */
    if (x !is String || x.length == 0) return

    /**
     * 这些 智能转换 用于 when-表达式 和 while-循环 也一样
     * */
    when (x) {
        is Int -> print(x + 1)
        is IntArray -> print(x.sum())
    }
}

/**
 * 变量的智能转换规则
 * - val 局部变量：总是可以，局部委托属性除外；
 * - val 属性：如果属性是 private 或 internal，或者该检查在声明属性的同一模块中执行。智能转换不适用于 open 的属性或者具有自定义 getter 的属性；
 * - var 局部变量：如果变量在检查和使用之间没有修改、没有在会修改它的 lambda 中捕获、并且不是局部委托属性；
 * - var 属性：决不可能（因为该变量可以随时被其他代码修改）
 * */
fun showAutoCastingLimit() {
    class Inner {
        private val name = "aaron"
        var age: Any = 25

        fun casting() {
            // val 属性的自动转换是安全的
            if (name is String) println("name length ${name.length}")

            // var 属性就不一样了，因为随时有可能被更改
            age = "what"
            if (age is Int) {
                // 不能自动转换为 Int
                // println(age.minus(2))
            }

            val localName = "Kevin"
            // 局部变量总是可以自动转换
            if (localName is String) println("localName length ${localName.length}")

            var localAge: Any = 25
            localAge = "what"
            if (localAge is Int) {
                println(localAge.plus(2))
            }
        }
    }

    Inner().casting()
}

/**
 * 如果转换是不可能的，转换操作符会抛出一个异常。因此，我们称之为不安全的。
 * Kotlin 中的不安全转换由中缀操作符 as 完成
 * */
fun notSafeCastingAs(y: Any) {
    val x: String = y as String

    println("Casting success")
}

/**
 * 为了避免抛出异常，可以使用安全转换操作符 as?，它可以在失败时返回 null
 * */
fun nullableNotSafeCasting(y: Any?) {
    val nullableX: String? = y as? String

    println("Casting success")
}


fun main() {
    testIsAndNot()

    autoCasting("Hello")

    showAutoCastingLimit()

//    notSafeCastingAs(12)

    nullableNotSafeCasting(null)
}