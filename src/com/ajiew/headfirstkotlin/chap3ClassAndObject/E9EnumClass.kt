package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 枚举类
 * https://www.kotlincn.net/docs/reference/enum-classes.html
 * */

/**
 * 枚举常量还实现了 Comparable 接口， 其中自然顺序是它们在枚举类中定义的顺序。
 * */
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

/**
 * 初始化
 * */
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    fun printColor() {
        println(rgb)
    }
}

/**
 * 匿名类
 * */
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

object EnumClass {

    @JvmStatic
    fun main(args: Array<String>) {
        val north = Direction.NORTH
        println("name = " + north.name + ", ordinal = " + north.ordinal)

        val value = Direction.valueOf("WEST")
        println("value of WEST = $value, position = " + value.ordinal)

        for (v in Direction.values()) {
            println("= $v")
        }

        println(ProtocolState.WAITING.signal())
        println(ProtocolState.TALKING.signal())

        // Enum 常量默认实现了 Comparable 接口，可以使用 compareTo 进行比较，其中的顺序是定义的顺序
        println(Direction.SOUTH.compareTo(Direction.EAST))
        println(Direction.SOUTH.compareTo(Direction.NORTH))
    }
}