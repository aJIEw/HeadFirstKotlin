// 可以使用 @JvmName 注解修改生成的 Java 类的类名：
@file:JvmName("CallKotlinJvmName")

package com.ajiew.headfirstkotlin.chap7JavaInterop


/**
 * Kotlin 文件中声明的所有的函数和属性，包括扩展函数，
 * 都会被编译成一个名为 包名.文件名 的 Java 类的静态方法
 * */
class Foo

fun bar() {
    println("Do something")
}

// Java
//new demo.Foo();
//demo.ExampleKt.bar();

/**
 * 实例字段
 * 如果需要在 Java 中将 Kotlin 属性作为字段暴露，那就需要使用 @JvmField 注解对其标注。
 * */
class Exposed(id: String) {
    @JvmField
    val ID = id
}

/**
 * 静态字段
 * 在命名对象或伴生对象中声明的 Kotlin 属性会在该命名对象或包含伴生对象的类中具有静态幕后字段。
 * 通常这些字段是私有的，但可以通过以下方式之一暴露出来：
 * - @JvmField 注解；
 * - lateinit 修饰符；
 * - const 修饰符。
 * */
class Key(val value: Int) {
    companion object {
        @JvmField
        val COMPARATOR: Comparator<Key> = compareBy { it.value }

        const val VERSION = 2
    }
}

object Singleton {
    lateinit var provider: String

    const val CONST = 1
}

const val MAX = 239

val someValue: String = "v"

fun main(args: Array<String>) {
    println(MAX)
    println(someValue)
}