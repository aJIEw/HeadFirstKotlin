package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 枚举类
 * https://www.kotlincn.net/docs/reference/enum-classes.html
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
    }
}