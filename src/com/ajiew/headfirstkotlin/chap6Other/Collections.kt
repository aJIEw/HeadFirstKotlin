package com.ajiew.headfirstkotlin.chap6Other

/**
 * 集合
 * https://www.kotlincn.net/docs/reference/collections.html
 * */

var shapes: List<Shape> = listOf(Rectangle("Desk"))

var mutableShapes: MutableList<Shape> = mutableListOf(Rectangle("Tablet"))

/**
 * Kotlin 的 List<out T> 类型是一个提供只读操作，如 size、get等的接口
 * 改变 list 的方法是由 MutableList<T> 加入的
 * */
fun showList() {
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    /* Mutable 可读可写 */
    println(numbers)
    numbers[0] = 10
    numbers.clear()

    val readOnlyNumbers: List<Int> = listOf(1, 2, 3) // 用 array list 实现
    // readOnlyNumbers[2] = 20 // 无法编译
    // readOnlyNumbers.clear() // 无法编译
}

/**
 * 对于 List 来说协变是允许的，但是 MutableList 不是
 * */
fun showCoherent() {
    val circles: List<Circle> = listOf(Circle("Sun"))

    shapes = circles
    println("shapes reassigned: $shapes")

    // 对于可变集合类型这是不允许的
    // mutableShapes = circles

    // 但是可以往里面添加
    mutableShapes.add(Circle("Wheel"))
    println("mutable shapes after add: $mutableShapes")

    /**
     * toList 扩展方法只是复制列表项
     * */
    mutableShapes.toList()

}

/**
 * List 有很多有用的扩展方法值得熟悉
 * */
fun listUtilFunctions() {
    /**
     * 获取快照，toList 只是复制列表项，因此返回的 list 保证永远不会改变
     * */
    mutableShapes.toList()

    shapes.firstOrNull()

    shapes.lastIndex

    shapes.last()

    shapes.subList(0 ,1)

    shapes.requireNoNulls() // 获取所有非空元素

    if (shapes.none { it.name == "Moon" }) println("No Moon Shape")

    println(shapes[0])
    println(shapes.get(0))
    println(shapes.component1()) // 都是 Circle(Sun)
}

/**
 * set 一些常用方法的示例
 * */
fun showSet() {
    val set = setOf(11, 22, 11, 24)

    println(set)

    println(set.min())

    set.minus(set.min()) // 获取除最小值外的新 set

    set.plus(33) // 与 minus 相反
}

/**
 * map 一些常用方法的示例
 * */
fun showMap() {
    val map = mapOf("one" to 1, "two" to 2, "three" to 3, 4 to "four")

    println(map[4])

    /**
     * 解构赋值
     * */
    for ((key, value) in map) {
        println("map[$key] = $value")
    }
}

fun main() {
    showList()

    showCoherent()

    listUtilFunctions()

    showSet()

    showMap()
}

open class Shape(val name: String) {
    override fun toString(): String {
        return this.javaClass.simpleName + "($name)"
    }
}

class Rectangle(name: String) : Shape(name) {
    init {
        println("init Rectangle -> $name")
    }
}

class Circle(name: String) : Shape(name) {
    init {
        println("init Circle -> $name")
    }
}
