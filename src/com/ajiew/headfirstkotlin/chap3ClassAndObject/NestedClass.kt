package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 嵌套类
 * https://www.kotlincn.net/docs/reference/nested-classes.html
 * */

class Outer {

    private val bar: String = "bar"

    private var listener: NestedListener? = null
        set(value) {
            if (value == null) {
                field = value
            }
        }

    /**
     * 普通的嵌套类
     * */
    class Nested {
        fun foo(): String = "nested"
    }

    /**
     * 内部类用 inner 标识
     * */
    inner class Inner {
        fun foo() = bar
    }

    fun addListener(listener: NestedListener) {
        this.listener = listener
    }
}

interface NestedListener {
    fun onClicked()

}

object NestedClass {

    @JvmStatic
    fun main(args: Array<String>) {
        val nested = Outer.Nested().foo()
        println(nested)

        val inner = Outer().Inner().foo()
        println(inner)

        val outer = Outer()
        /**
         *  使用对象表达式创建匿名内部类
         * */
        outer.addListener(object : NestedListener {

            override fun onClicked() {

            }
        })
    }
}