package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 密封类
 * https://www.kotlincn.net/docs/reference/sealed-classes.html
 * */

/**
 * 密封类用来表示受限的类继承结构：当一个值为有限集中的类型、而不能有任何其他类型时。
 *
 * 在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。
 *
 * 所有密封类的子类都必须写在同一个文件之中
 * */
sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr() {

    fun eval(expr: Expr): Double = when (expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.NaN // 注释掉以上其中一个就会造成需要 else
        // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
        else -> 0.0 // no need
    }

    @JvmStatic
    fun main(args: Array<String>) {
    }
}