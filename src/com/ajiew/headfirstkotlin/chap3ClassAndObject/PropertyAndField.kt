package com.ajiew.headfirstkotlin.chap3ClassAndObject

/**
 * 属性与字段
 * https://www.kotlincn.net/docs/reference/properties.html
 * */

/**
 * 编译期常量，使用 const val 声明，只允许基本数据类型和 String
 * */
const val OS: String = "Mac OS X Mojave 10.14"

/**
 * 声明属性
 * var <propertyName>[: <PropertyType>] [= <property_initializer>]
 * [<getter>]
 * [<setter>]
 * */
class Property(name: String) {

    var size: Int = 0
        set(value) {
            /**
             * 给属性赋值需要使用幕后字段，Kotlin 中通过 field 关键字引用幕后字段 (backing field)
             * */
            if (value >= 0) field = value
        }

    val isEmpty
        get() = this.size == 0

    /**
     * 幕后属性 (backing property)
     * 对外表现为只读，对内表现为可读可写，我们将这个属性称为幕后属性。
     * */
    private var _table: Map<String, Int>? = null
    /**
     * 外部只能通过 table 来访问，但实际上访问的是 _table，所以我们此时称 _table 为幕后属性
     * 这一点类似于 Java 中通过设置 Getter 和 Setter 来控制对类属性点访问
     * */
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    /**
     * 延迟初始化，使用关键字 lateinit，使用场景可以是：使用依赖注入或者单元测试的 setup 中初始化的时候
     * */
    lateinit var owner: People

    init {
        owner = People("Aaron")
        println("init main Constructor: owner is ${owner.name}")
    }
}

object PropertyAndField {

    @JvmStatic
    fun main(args: Array<String>) {
        val p = Property("IntelliJ")
        p.size = 2 // 将调用 Setter

        println(p.isEmpty)
    }
}