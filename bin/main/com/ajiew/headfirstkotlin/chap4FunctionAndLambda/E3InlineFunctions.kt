package com.ajiew.headfirstkotlin.chap4FunctionAndLambda

import java.util.concurrent.locks.Lock

/**
 * 内联函数
 * https://www.kotlincn.net/docs/reference/inline-functions.html
 * */

/**
 * 高阶函数的问题
 * 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。 即那些在函数体内会访问到的变量。
 * 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。
 * =======
 * 内联函数
 * =======
 * inline 修饰符影响函数本身和传给它的 lambda 表达式：所有这些都将内联到调用处。
 * */
inline fun <T> lock(lock: Lock, body: () -> T): T {
    println("do something")
    return body()
}

/**
 * 内联可能导致生成的代码增加；
 * 不过如果我们使用得当（即避免内联过大函数），性能上会有所提升，尤其是在循环中的“超多态（megamorphic）”调用处。
 * */
