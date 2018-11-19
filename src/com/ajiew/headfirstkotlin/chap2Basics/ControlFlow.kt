package com.ajiew.headfirstkotlin.chap2Basics


/**
 * 控制流
 * https://www.kotlincn.net/docs/reference/control-flow.html
 * */

fun basicForLoop() {
    for (i in 3..9) {
        println(i)
    }
}

fun loopArray(args: Array<String>) {
    for (i in args.indices) {
        println(args[i])
    }
}

fun main() {
    basicForLoop()

    val array = arrayOf("a", "b", "c")
    loopArray(array)
}