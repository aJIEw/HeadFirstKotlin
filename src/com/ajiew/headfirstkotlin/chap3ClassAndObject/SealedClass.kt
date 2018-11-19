package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 密封类
 * https://www.kotlincn.net/docs/reference/sealed-classes.html
 * */

/**
 * 密封类用来表示受限的类继承结构：当一个值为有限集中的类型、而不能有任何其他类型时。
 * */
sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr() {

    fun eval(expr: Expr): Double = when(expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.NaN
        // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}