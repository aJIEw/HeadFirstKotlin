package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 伴生对象，可以直接引用，相当于静态内部类
 * https://www.kotlincn.net/docs/reference/object-declarations.html#%E4%BC%B4%E7%94%9F%E5%AF%B9%E8%B1%A1
 * */

class MyClass {

    val v = 1

    /**
     * 类内部的对象声明可以用 companion 关键字标记
     * */
    companion object Factory {
//        val copyV = v // 无法访问外部类

        val whatever = "ok"

        fun create(): MyClass = MyClass()
    }

    override fun toString(): String {
        return "MyClass"
    }
}

/**
 * 即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员，而且，例如还可以实现接口
 * */
interface Factory<T> {
    fun create(): T
}

class MyClass2 {
    companion object : Factory<MyClass2> {
        override fun create(): MyClass2 = MyClass2()
    }
}

object Companion {

    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * 可以直接访问伴生对象的属性和方法
         * */
        println(MyClass.whatever)

        val instance = MyClass.create()
        println(instance.toString())

        /* 效果和上面是一样的，所以伴生类的类名可以省略 */
        val companion = MyClass.Factory.create()
        println(companion)
    }
}