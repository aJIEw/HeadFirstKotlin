package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 类的继承与初始化
 * https://www.kotlincn.net/docs/reference/classes.html#%E7%BB%A7%E6%89%BF
 * */

/**
 * 使用 open 关键字表示允许子类继承
 * */
open class Human /*@Inject public*/ constructor(name: String) {
    /**
     * 初始代码块中可以访问主构造函数中的参数
     * */
    init {
        println("First initializer block that prints $name")
    }

    /**
     * 也可以在属性初始化器中使用
     * */
    val firstName = name.toUpperCase()

    fun sayName() {
        println("My name is ${this.javaClass}")
    }
}

/**
 * 如果主构造函数没有可见性修饰符或者注解，可以省略 constructor 关键字
 * */
open class Father(name: String) : Human(name) {
    /**
     * 方法上必需使用 open 子类才能复写
     * */
    open fun job() {
        println("I'm ${Father::class.simpleName}, I'm a cooker")
    }
}

/**
 * 如果父类中含有构造函数，那么子类在继承时必须初始化该构造函数
 * */
class Son(name: String, val age: Int) : Father(name) {

    init {
        println("init block in Son for $name")
    }

    /**
     * 次构造函数必须委托给主构造函数
     * */
    constructor(name: String, age: Int, sex: String) : this(name, age) {
        println("initiated with sex")
    }

    /**
     * 继承方法必需使用 override 关键字
     * */
    override fun job() {
        super.job()
        println("I'm ${Son::class.simpleName}, I'm an advanced cooker")
    }
}

/**
 * 当然也可以通过次级构造函数来初始化
 * */
class Daughter : Human {
    constructor(name: String) : super(name)
}

/**
 * 如果主构造函数的所有的参数都有默认值，编译器会生成一个额外的无参构造函数，它将使用默认值
 * */
class Customer(val customerName: String = "No One")

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
        val son = Son("Kevin", 25, "M")
        son.job()

        println()

        val c = Customer()
        println(c.customerName)
    }
}
