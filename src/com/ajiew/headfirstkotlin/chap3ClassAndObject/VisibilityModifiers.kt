package com.ajiew.headfirstkotlin.chap3ClassAndObject


/**
 * 可见性修饰符
 * https://www.kotlincn.net/docs/reference/visibility-modifiers.html
 * */

/**
 * ========================================
 * 如果不指定任何可见性修饰符，**默认为 public**
 * ========================================
 * */
class Whatever {

    /**
     * 能见到类声明的任何客户端都可见其 public 成员
     * */
    public var visible: String = "public"

    var v2: String = "default is public"

    /**
     * internal 能见到类声明的本模块内的任何客户端都可见其 internal 成员
     *
     * 模块指是编译在一起的一套 Kotlin 文件:
     * - 一个 IntelliJ IDEA 模块；
     * - 一个 Maven 项目；
     * - 一个 Gradle 源集；
     * - 一次 <kotlinc> Ant 任务执行所编译的一套文件.
     * */
    internal val half: String = "internal"

    /**
     * protected 和 private 一样 + 在子类中可见
     * *
     * 注意：
     * - 不适用于顶层声明
     * - 如果你覆盖一个 protected 成员并且没有显式指定其可见性，该成员还会是 protected 可见性。
     * */
    protected val less: String = "protected"

    /**
     * 如果你声明为 private，它只会在声明它的文件内可见
     * *
     * 注意：
     * - Kotlin 中外部类不能访问内部类的 private 成员
     * */
    private val notVisible: Int = 2

}

object VisibilityModifiers {
    @JvmStatic
    fun main(args: Array<String>) {
    }
}