package com.ajiew.headfirstkotlin.chap6Other

/**
 * 操作符重载
 * https://www.kotlincn.net/docs/reference/operator-overloading.html
 * */

data class Point(val x: Int, val y: Int)

/**
 * 重载操作符的函数需要用 operator 修饰符标记
 * - 操作符对应的函数为 unaryMinus，所以当我们重载了该操作符后，则 - 操作符调用的函数发生更改
 * */
operator fun Point.unaryMinus() = Point(-x, -y)

val point = Point(10, 20)

/**
 * 一元操作符
 * +a	a.unaryPlus()
 * -a	a.unaryMinus()
 * !a	a.not()
 *
 * 相等与不等操作符
 * a == b	a?.equals(b) ?: (b === null)
 * a != b	!(a?.equals(b) ?: (b === null))
 *
 * 递增与递减
 * a++	a.inc() + 见下文
 * a--	a.dec() + 见下文
 * https://www.kotlincn.net/docs/reference/operator-overloading.html#%E9%80%92%E5%A2%9E%E4%B8%8E%E9%80%92%E5%87%8F
 *
 * 二元操作
 * a + b	a.plus(b)
 * a - b	a.minus(b)
 * a * b	a.times(b)
 * a / b	a.div(b)
 * a % b	a.rem(b)、 a.mod(b) （已弃用）
 * a..b	    a.rangeTo(b)
 *
 * 比较操作符
 * a > b	a.compareTo(b) > 0
 * a < b	a.compareTo(b) < 0
 * a >= b	a.compareTo(b) >= 0
 * a <= b	a.compareTo(b) <= 0
 *
 * in 操作符
 * a in b	b.contains(a)
 * a !in b  !b.contains(a)
 *
 * 索引访问操作符
 * a[i]	                a.get(i)
 * a[i, j]	            a.get(i, j)
 * a[i_1, ……, i_n]	    a.get(i_1, ……, i_n)
 * a[i] = b	            a.set(i, b)
 * a[i, j] = b	        a.set(i, j, b)
 * a[i_1, ……, i_n] = b	a.set(i_1, ……, i_n, b)
 *
 * 调用操作符
 * a()	                a.invoke()
 * a(i)	                a.invoke(i)
 * a(i, j)	            a.invoke(i, j)
 * a(i_1, ……, i_n)	    a.invoke(i_1, ……, i_n)
 *
 * 广义赋值
 * a += b	a.plusAssign(b)
 * a -= b	a.minusAssign(b)
 * a *= b	a.timesAssign(b)
 * a /= b	a.divAssign(b)
 *
 * 属性委托操作符
 * provideDelegate、 getValue 以及 setValue 操作符函数已在委托属性中描述
 * */
fun main() {
    println(-point)  // Point(x=-10, y=-20)
}