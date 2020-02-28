package com.ajiew.headfirstkotlin.chap2Basics


/**
 * 集合
 * https://www.kotlincn.net/docs/reference/collections.html
 * */
fun main() {
    showList()

    showCoherent()

    listUtilFunctions()

    showSet()

    showMap()

    testCollection()
}

var shapes: List<Shape> = listOf(Rectangle("Desk"))

var mutableShapes: MutableList<Shape> = mutableListOf(Rectangle("Tablet"))

/**
 * Kotlin 的 List<out T> 类型是一个提供只读操作，如 size、get等的接口
 * 改变 list 的方法是由 MutableList<T> 加入的
 * */
fun showList() {
    println("--------------------------showList--------------------------")
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    /* MutableList 可读可写 */
    println(numbers)
    numbers[0] = 10
    numbers.clear()

    val readOnlyNumbers: List<Int> = listOf(1, 2, 3) // 用 List 实现，List 是只读的
    // readOnlyNumbers[2] = 20 // 无法编译
    // readOnlyNumbers.clear() // 无法编译
    println(readOnlyNumbers)
}

fun showCoherent() {
    println("--------------------------showCoherent--------------------------")
    val circles: List<Circle> = listOf(Circle("Sun"))

    shapes = circles
    println("shapes reassigned: $shapes")

    // 不可变集合类型不允许转换为可变集合类型
    // mutableShapes = circles

    // 但是可以往里面添加
    mutableShapes.add(Circle("Wheel"))
    println("mutable shapes after add: $mutableShapes")

    // 转换成可变集合
    mutableShapes = circles.toMutableList()

    /**
     * toList 扩展方法只是复制列表项
     * */
    val copied = mutableShapes.toList()
    println(copied)
}

/**
 * List 有很多有用的扩展方法值得熟悉
 * */
fun listUtilFunctions() {
    println("--------------------------listUtilFunctions--------------------------")
    /**
     * 获取快照，toList 只是复制列表项，因此返回的 list 保证永远不会改变
     * */
    mutableShapes.toList()

    shapes.firstOrNull()

    shapes.lastIndex

    shapes.last()

    shapes.subList(0, 1)

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
    println("--------------------------showSet--------------------------")
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
    println("--------------------------showMap--------------------------")
    // 前面是 key 后面时 value
    val map = mapOf("one" to 1, "two" to 2, "three" to 3, 4 to "four")

    println("map[one] = ${map["one"]}")

    /**
     * 遍历 key value
     * */
    for ((key, value) in map) {
        println("map[$key] = $value")
    }
}

fun testCollection() {
    println("--------------------------testCollection--------------------------")
    val firstShape = shapes[0]
    println("firstShape: $firstShape")

    val last = shapes.last()
    println("shapes.lastIndex = ${shapes.lastIndex}, last = $last")

    val subList = shapes.subList(0, shapes.size)
    println("subList: $subList")

    for (shape in shapes) {
        println(shape)
    }

    val s = listOf("Aaron", "Chen")
    val joinToString = joinToString(s, "-", "Pre ", " Post")
    println(joinToString)
}

fun <T> joinToString(collection: Collection<T>,
                     separator: String = ", ",
                     prefix: String = "",
                     postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/**
 * 使用 @JvmOverloads 来实现函数的重载，这样在 Java 中也可以调用
 * */
@JvmOverloads
fun <T> joinToString2(collection: Collection<T>,
                      separator: String = ", ",
                      prefix: String = "PRE ",
                      postfix: String = " POST"): String {
    return joinToString(collection, separator, prefix, postfix)
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
