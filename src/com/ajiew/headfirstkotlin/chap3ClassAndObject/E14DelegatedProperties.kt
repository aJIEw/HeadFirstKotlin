package com.ajiew.headfirstkotlin.chap3ClassAndObject

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托属性
 * https://www.kotlincn.net/docs/reference/delegated-properties.html
 * */

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

/**
 * 有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。
 * 例如包括：
 * - 延迟属性（lazy properties）: 其值只在首次访问时计算；
 * - 可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；
 * - 把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
 * */
class Example {
    /**
     * 语法是：val/var <属性名>: <类型> by <表达式>
     * 在 by 后面的表达式是该委托，因为属性对应的 get() 与 set() 会被委托给它的 getValue() 与 setValue() 方法。
     * 属性的委托不必实现任何的接口，但是需要提供一个 getValue() 函数（与 setValue()——对于 var 属性）
     * */
    var p: String by Delegate()

}

/**
 * 1、延迟属性
 * lazy() 是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托。
 * 默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）
 * */
val lazyValue: String by lazy {
    print("computed!")
    "Hello"
}

/**
 * 2、可观察属性 Observable
 * Delegates.observable() 接受两个参数：初始值与修改时处理程序（handler）
 * 每当我们给属性赋值时会调用该处理程序（在赋值后执行）。它有三个参数：被赋值的属性、旧值与新值
 * */
class User {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$old -> $new")
    }
}

/**
 * 把属性储存在映射（map）中
 * */
class Student(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

val student = Student(mapOf(
    "name" to "Aaron Chen",
    "age" to 25
))

/**
 * 这也适用于 var 属性，如果把只读的 Map 换成 MutableMap 的话：
 * */
class MutableStudent(val map: MutableMap<String, Any>) {
    var name: String by map
    var age: Int     by map
}

fun main() {
    val e = Example()
    /**
     * 将调用 Delegate 中的 getValue() 函数
     * */
    println(e.p)

    /**
     * 将调用 Delegate 中的 setValue() 函数
     * */
    e.p = "New Value"

    println(lazyValue) // computed!Hello
    println(lazyValue) // Hello

    val user = User()
    user.name = "first"
    user.name = "second"

    println(student.name) // Prints "John Doe"
    println(student.age)  // Prints 25

    val content = mutableMapOf(
        "name" to "Aaron Chen",
        "age" to 24)
    var mStu = MutableStudent(content)
    println("name: ${mStu.name}, age: ${mStu.age}")
}
