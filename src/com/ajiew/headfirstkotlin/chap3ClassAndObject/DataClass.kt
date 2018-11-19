package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 数据类
 * https://www.kotlincn.net/docs/reference/data-classes.html
 * */

/**
 * 编译器自动从主构造函数中声明的所有属性导出以下成员：
 * - equals()/hashCode()
 * - toString() 格式是 "User(name=John, age=42)"
 * - componentN() 函数 按声明顺序对应于所有属性
 * - copy() 函数
 * */
data class Member(val name: String, val age: Int)
