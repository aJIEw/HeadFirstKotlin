package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 接口
 * https://www.kotlincn.net/docs/reference/interfaces.html
 * */

/**
 * 声明接口，接口中可以有抽象属性和方法体
 * */
interface MyInterface {

    val prop: Int // 抽象的，子类必须实现，或者提供访问器

    fun bar()
    fun foo() {
        // 可选的方法体，如果有方法体则子类不用必须实现
        println("foo in MyInterface")
    }
}

class MyInterfaceImpl : MyInterface {

    override val prop: Int = 29 // 子类必须复写抽象属性

    override fun bar() {
        // 方法体
        println("bar in MyInterfaceImpl")
    }

    /**
     * foo 其实可以不实现
     * */
    override fun foo() {
        super.foo() // 与 Java 中类似，调用接口中的方法使用 super
        println("foo in MyInterfaceImpl")
    }
}

interface Named {
    val name: String
}

/**
 * 接口继承
 * */
interface Person : Named {
    val firstName: String
    val lastName: String

    // 实现了访问器之后子类就不必复写该抽象属性了
    override val name: String get() = "$firstName $lastName"
}

/**
 * 主构造函数中覆写父类中的变量
 * */
data class Employee(
    // 不必实现“name”，因为父类已经提供了访问器
    override val firstName: String,
    override val lastName: String
) : Person

/**
 * 下面演示 Kotlin 中同一个方法继承多个实现的问题
 * */
interface A {
    fun foo() {
        print("A")
    }

    fun bar()
}

interface B {
    fun foo() {
        print("B")
    }

    fun bar() {
        print("bar")
    }
}

/**
 * C 继承 A，只要实现非抽象类方法 bar() 就可以了
 * */
class C : A {
    override fun bar() {
        print("bar")
    }
}

/**
 * D 继承 A 和 B，继承 foo() 方法时会同时获得两个父类中不同的实现
 *
 * */
class D : A, B {
    override fun foo() {
        super<A>.foo() // 使用 super<> 的形式调用父类中的方法
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar() // 由于只有 B 实现了 bar，所以只能调用 B 中的实现，等同于 super.bar()
    }
}


object Interface {

    @JvmStatic
    fun main(args: Array<String>) {
        val impl = MyInterfaceImpl()
        println("Calling foo()=>")
        impl.foo()
        println("Calling bar()=>")
        impl.bar()
    }
}
