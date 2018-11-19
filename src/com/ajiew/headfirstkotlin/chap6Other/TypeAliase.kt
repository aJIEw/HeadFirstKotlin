package com.ajiew.headfirstkotlin.chap6Other

import java.io.File

/**
 * 类型别名
 * https://www.kotlincn.net/docs/reference/type-aliases.html
 * */

/**
 * 类型别名为现有类型提供替代名称。
 * 如果类型名称太长，你可以另外引入较短的名称，并使用新的名称替代原类型名。
 * 它有助于缩短较长的泛型类型，比如缩减集合类型
 * */
typealias FileTable<K> = MutableMap<K, MutableList<File>>


/**
 * 也可以为函数类型提供别名
 * */
typealias MyHandler = (Int, String, Any) -> Unit

class A {
    inner class Inner
}

/**
 * 为内部类和嵌套类创建新名称
 * */
typealias AInner = A.Inner

fun main() {
    val f: FileTable<String> = mutableMapOf("Key" to mutableListOf(File("Key.file")))
}


