package com.ajiew.headfirstkotlin.chap6Other

/**
 * this 表达式
 * https://www.kotlincn.net/docs/reference/this-expressions.html
 * */

/**
 * 为了表示当前的 接收者 我们使用 this 表达式：
 * - 在类的成员中，this 指的是该类的当前对象。
 * - 在扩展函数或者带有接收者的函数字面值中， this 表示在点左侧传递的**接收者**参数。
 * */
fun showThis() {
    class A { // 隐式标签 @A
        inner class B { // 隐式标签 @B
            fun Int.foo() { // 隐式标签 @foo
                val a = this@A // A 的 this
                val b = this@B // B 的 this

                println(a)
                println(b)

                val c = this // foo() 的接收者，一个 Int
                val c1 = this@foo // foo() 的接收者，一个 Int

                println(c)
                println(c1)

                val funLit = lambda@ fun String.() {
                    val d = this // funLit 的接收者
                }


                val funLit2 = { s: String ->
                    // foo() 的接收者，因为它包含的 lambda 表达式
                    // 没有任何接收者
                    val d1 = this
                }
            }
        }

    }
}

fun main() {
    showThis()
}