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

    /**
     * 幕后字段(backing field)主要用于属性在访问器中(Getter/Setter)使用，前提是使用了默认访问器或者显式调用 field
     * 如果有一个访问器使用了默认实现，那么 Kotlin 就会自动提供幕后字段
     * 有初始化器则必须有幕后字段，反之亦然
     * */
    var size: Int = 0
        set(value) {
            /**
             * 给属性赋值需要使用幕后字段，Kotlin 中通过 field 关键字引用幕后字段
             * */
            if (value >= 0) field = value
        }

    var isEmpty = false
//        get() = this.size == 0 // 修改 get 的实现
        set(value) {
            println("You have to set \"field\" manually to create a backing field")
            // 如果不手动调用 field 会提醒不允许初始化属性，因为我们实现了初始化器但却没有使用幕后字段。
            // 可以尝试注释掉下面这行：
            field = value

            /*
            * 其本质是因为 Kotlin 中使用 getter setter 来访问以及给属性赋值
            * 而 getter setter 又通过 backing field 来访问或者赋值，如果没有幕后字段，赋值就会变成死循环
            * */
            // isEmpty = value // Recursive property accessor
        }

    /**
     * 由于是只读 val，没有 setter，同时又重写了 get 访问器而且不引用 field，所以此种情况下没有 backing field
     * */
    val noBackingField: Boolean
        get() = this.size == 0
//        get() = field // 如果直接返回 filed 则必须初始化，因为存在 backing field

    /**
     * 幕后属性 (backing property)
     * 对外表现为只读，对内表现为可读可写，我们将这个属性称为幕后属性。
     * */
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    /**
     * 外部只能通过 table 来访问，但实际上访问的是 _table，所以我们此时称 _table 为幕后属性
     * 这一点类似于 Java 中通过设置 Getter 和 Setter 来控制对类属性的访问
     * */
    private var _table: Map<String, Int>? = null

    /**
     * 延迟初始化，使用关键字 lateinit，使用场景可以是：使用依赖注入或者单元测试的 setup 中初始化的时候
     * */
    lateinit var owner: People

    init {
        // 如果在初始化前调用 lateinit 的属性将抛出异常
        owner = People("Aaron")
        println("\ninit main Constructor: owner is ${owner.name}")
    }

    fun main() {
        // 通过 isInitialized 判断值是否初始化
        if (this::owner.isInitialized) {
        }
    }
}

object PropertyAndField {

    @JvmStatic
    fun main(args: Array<String>) {
        val p = Property("IntelliJ")
        p.size = 2 // 将调用 Setter

        println(p.isEmpty)

        println("try set isEmpty to true....")
        p.isEmpty = true

        // 修改 get 的实现再来看下面的值是否能修改成功
        println(p.isEmpty)
    }
}