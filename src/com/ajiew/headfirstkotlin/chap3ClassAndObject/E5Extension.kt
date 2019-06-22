package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 类的扩展
 * https://www.kotlincn.net/docs/reference/extensions.html
 * */

/**
 * 扩展一般写在顶层，我们可以通过通过扩展给一些类添加一些 Util 方法等
 * */
object Extension {

    /**
     * 扩展 MutableList 添加一个 swap 方法
     * */
    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    /**
     * 扩展属性
     * */
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    @JvmStatic
    fun main(args: Array<String>) {
        val list = mutableListOf('1', '2', '3')
        /**
         * 通过定义一个扩展，你并没有在一个类中插入新成员
         * 仅仅是可以通过该类型的变量用点表达式去调用这个新函数
         * */
        list.swap(0, 2)
        println(list)

        /**
         * 调用扩展属性
         * */
        println("last index is ${list.lastIndex}")

        /**
         * 调用的永远是 ExtensionA 中的扩展函数
         * */
        printFoo(ExtensionB())

        main()
    }

    /**
     * 扩展声明为成员
     * */
    class A {
        fun bar() {}
    }

    /**
     * 在一个类内部你可以为另一个类声明扩展
     * */
    class B {
        fun baz() {}

        /**
         * 此时 B 是 分发接收者，A 是 扩展接收者
         * */
        fun A.foo() {
            bar()   // calls A.bar
            baz()   // calls B.baz

            // 扩展接收者 优先
            toString()         // 调用 A.toString()
            this@B.toString()  // 调用 B.toString()
        }
    }

    /**
     * 扩展接收者
     * */
    open class D

    class D1 : D()

    /**
     * 分发接收者
     * */
    open class C {
        open fun D.foo() {
            println("D.foo in C")
        }

        open fun D1.foo() {
            println("D1.foo in C")
        }

        fun caller(d: D) {
            d.foo()   // 调用扩展函数
        }
    }

    class C1 : C() {
        override fun D.foo() {
            println("D.foo in C1")
        }

        override fun D1.foo() {
            println("D1.foo in C1")
        }

    }

    fun main() {
        // 调用 C 中 D 的扩展
        C().caller(D())   // 输出 "D.foo in C"

        // 调用 C1 中 D 的扩展，分发接收者虚拟解析
        C1().caller(D())  // 输出 "D.foo in C1"

        // 调用 C 中 D 的扩展，扩展接收者静态解析
        C().caller(D1())  // 输出 "D.foo in C"
    }
}

open class ExtensionA

class ExtensionB : ExtensionA()

fun ExtensionA.foo() = "ExtensionA"

fun ExtensionB.foo() = "ExtensionB"

fun printFoo(a: ExtensionA) {
    /**
     * 扩展是静态解析的
     *
     * 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的，而不是由表达式运行时求值结果决定的。
     * */
    println(a.foo())
}
