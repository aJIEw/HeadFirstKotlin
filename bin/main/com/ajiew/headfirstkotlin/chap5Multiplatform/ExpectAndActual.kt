package com.ajiew.headfirstkotlin.chap5Multiplatform

/**
 * 多平台
 * https://www.kotlincn.net/docs/reference/platform-specific-declarations.html
 * */

/**
 * 多平台目前还处于实验状态，需要开启才能使用
 * A common module can define expected declarations,
 * and a platform module can provide actual declarations corresponding to the expected ones.
 * */
/*
expect class Foo(bar: String) {
    fun frob()
}

actual class Foo actual constructor(val bar: String) {
    actual fun frob() {
        println("Frobbing the $bar")
    }
}

fun main() {
    Foo("Hello").frob()
}
*/