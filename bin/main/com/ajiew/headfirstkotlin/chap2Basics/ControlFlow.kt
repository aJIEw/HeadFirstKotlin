package com.ajiew.headfirstkotlin.chap2Basics


/**
 * 控制流
 * https://www.kotlincn.net/docs/reference/control-flow.html
 * */

fun basicForLoop() {
    for (i in 3..9) {
        print("$i, ")
    }
}

fun loopArray(args: Array<String>) {
    for (i in args.indices) {
        print(args[i] + ',')
    }
}

fun main() {
    basicForLoop()

    println()

    val array = arrayOf("a", "b", "c")
    loopArray(array)
}