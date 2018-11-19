package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 类的继承
 * https://www.kotlincn.net/docs/reference/classes.html#%E7%BB%A7%E6%89%BF
 * */

/**
 * 使用 open 关键字表示允许子类继承
 * */
open class Father {
    /**
     * 方法上必需使用 open 子类才能复写
     * */
    open fun job() {
        println("I'm a cooker")
    }

    fun sayName() {
        println("My name is ${this.javaClass}")
    }
}

class Son : Father() {
    /**
     * 继承方法必需使用 override 关键字
     * */
    override fun job() {
        super.job()
        println("I'm an advanced cooker")
    }
}

/**
 * 抽象类
 * */
abstract class Store {

    val name = "Store"

    /**
     * 抽象方法
     * */
    fun sayStoreName() {
        println(name)
    }

    abstract fun showGoods()
}

class GroceryStore : Store() {
    override fun showGoods() {
        println("Food Section")
        println("Living Things Section")
        println("Other Stocks Section")
    }

}


object OpenAndOverride {

    @JvmStatic
    fun main(args: Array<String>) {
        val son = Son()
        son.job()
    }
}
