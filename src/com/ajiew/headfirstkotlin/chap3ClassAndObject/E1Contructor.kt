package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 类的构造函数
 * https://www.kotlincn.net/docs/reference/classes.html#%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0
 * */

/**
 * 声明类属性可以直接在主构造函数中添加 val 或 var，并且可以省略 constructor 关键字，只要没有注解或者可见性修饰符
 * */
open class People(val name: String){

    /**
     * 初始代码块中的代码会在次构造函数之前执行
     * */
    init {
        println("My name is $name")
    }

    /**
     * 直接使用 name
     * */
    val nameLength = name.length

    /**
     * 次构造函数中不可以声明类属性，而且必需先使用 this 委托调用主构造函数或者其他次构造函数，如果主构造函数存在的话（默认为空）
     * */
    constructor(name: String, age: Int) :this(name) {
        println("and I have age")
    }

}


object Constructor{

    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * Kotlin 中没有 new 关键字，直接调用类名的构造函数就可以了
         * */
        val p1 = People("Aaron")
        println(p1.name)
        val p2 = People("Kevin", 24)
        println(p2.name)
    }
}