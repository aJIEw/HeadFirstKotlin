package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 对象表达式与对象声明
 * https://www.kotlincn.net/docs/reference/object-declarations.html
 * */

open class SomeParentClass {
    open fun someFun() {
        println("do some stuff here")
    }
}

/**
 * 如果我们只需要一个匿名对象，并不需要特殊超类型，那么我们可以简单地声明 object 就可以了
 * */
fun objectDeclaration() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

/**
 * 注意，匿名对象可以用作只在本地和私有作用域中声明的类型。
 * */
class AnonymousObject {

    var flag = 0

    /**
     * 私有函数，所以其返回类型是匿名对象类型
     * */
    private fun foo() = object {
        val x: String = "x"

        /**
         * 就像 Java 匿名内部类一样，对象表达式中的代码可以访问来自包含它的作用域的变量。
         * */
        fun bar() {
            flag++
        }
    }

    /**
     * 公有函数，所以其返回类型是 Any
     * */
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
//        val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }

}

/**
 * object 后跟一个名称，这称为『对象声明』，生成的对象称为 named object，默认是单例的，而且是线程安全的
 * */
object Objects {

    fun someFun() {
        println("someFun() do something here")
    }

    @JvmStatic
    fun main(args: Array<String>) {

        /**
         * 有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。 Java 用匿名内部类 处理这种情况。
         * Kotlin 中可以使用『对象表达式』来做到相同的事情
         * */
        val another = object : SomeParentClass() {
            override fun someFun() {
                super.someFun()
                println("do some other stuff")
            }
        }


        someFun()

        another.someFun()
    }
}