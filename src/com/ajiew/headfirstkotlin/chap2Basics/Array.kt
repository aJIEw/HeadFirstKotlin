package com.ajiew.headfirstkotlin.chap2Basics


/**
 * Array 除了可以调用以下方法外，也可以调用 kotlin.collections 中的方法
 * */
fun main() {
    testArray()
}

fun testArray() {
    val names = arrayOf("Maeve", "Carol", "Shelley")

    // 不但可以使用下标
    println("Second is ${names[1]}")

    // 也可以使用 get set
    names.set(0, "Monica")
    println("First is ${names.get(0)}")

    println("names array size is ${names.size}")

    val plusElement = names.plusElement("Rachel")
    println("plusElement: ${plusElement.toList()}")
    val plus = plusElement.plus(arrayOf("Aaron", "Kevin")) // 也可以添加 Collection
    println("plus: ${plus.toList()}")

    // 从第二个到最后一个(fromIndex 和 toIndex 均不包括)，填充为 null
    plusElement.fill("null", 1, plusElement.size)
    println("filled: ${plusElement.toList()}")

    // 反转
    names.reverse()
    println("names are reversed: ${names.toList()}")
    val reversedArray = names.reversedArray()
    println("reversedArray: ${reversedArray.toList()}")
    val reversed = names.reversed() // kotlin.collections 中的方法，转换为反转的 List
    println("reversed: $reversed")

    // 排序
    val sortedArray = names.sortedArray()
    println("sortedArray: ${sortedArray.toList()}")
    val sortedArrayDescending = names.sortedArrayDescending()
    println("sortedArrayDescending: ${sortedArrayDescending.toList()}")
    val sorted: Any = names.sorted()
    println("sorted: $sorted") // kotlin.collections 中的方法，转换为排序后的 List

    // 复制
    val copy = names.copyOf()
    val copyOfRange = names.copyOfRange(0, names.size)
    println("copyOfRange: ${copyOfRange.toList()}")

    // 切片
    val sliceArray = copy.sliceArray(1 until copy.size)
    println("sliceArray: ${sliceArray.toList()}")
}