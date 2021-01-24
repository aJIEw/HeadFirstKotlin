package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 嵌套类与内部类 Nested and Inner Classes
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

    class Nested {
        fun foo(): String = "nested"

        fun bar() {
//            println(bar) // 嵌套类不能访问外部类中的 bar
        }
    }

    /**
     * A nested class marked as inner can access the members of its outer class
     * */
    inner class Inner {
        fun foo() = bar

        fun bar() {
            // 使用 super@外部类名称.method 的方式调用外部方法或者属性
            super@Outer.toString()
            println(bar) // 可以访问 bar
        }
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

        outer.Inner().bar()
    }
}