package com.ajiew.headfirstkotlin.chap6Other

/**
 * 注解
 * https://www.kotlincn.net/docs/reference/annotations.html
 * */

/**
 * 注解是将元数据附加到代码的方法，使用 annotation 声明注解类
 *
 * 注解的附加属性可以通过用元注解标注注解类来指定：
 * - @Target 指定可以用该注解标注的元素的可能的类型（类、函数、属性、表达式等）
 * - @Retention 指定该注解是否存储在编译后的 class 文件中，以及它在运行时能否通过反射可见 （默认都是 true）
 * - @Repeatable 允许在单个元素上多次使用相同的该注解
 * - @MustBeDocumented 指定该注解是公有 API 的一部分，并且应该包含在生成的 API 文档中显示的类或方法的签名中
 * */
@Target(AnnotationTarget.CLASS,         // 类
    AnnotationTarget.FUNCTION,          // 函数
    AnnotationTarget.VALUE_PARAMETER,   // 参数
    AnnotationTarget.FIELD,             // 属性
    AnnotationTarget.PROPERTY_GETTER,   // Getter
    AnnotationTarget.CONSTRUCTOR)       // 构造函数
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Fancy

/**
 * 注解可以有参数
 *
 * 允许的参数类型有：
 * - 对应于 Java 原生类型的类型（Int、 Long等）；
 * - 字符串；
 * - 类（Foo::class）；
 * - 枚举；
 * - 其他注解；
 * - 上面已列类型的数组。
 * */
annotation class Special(val why: String)

/**
 * 用法
 * */
@Fancy
class Foo {
    @Fancy
    fun baz(@Fancy foo: Int): Int {
        return 1
    }
}

/**
 * 构造器上的注解
 * */
//class Foo @Inject constructor(dependency: MyDependency) { …… }

/**
 * 注解也可以使用在 Lambda 表达式上
 * */
val f = @Fancy { println("whatever") }

/**
 * 注解目标使用处
 * 当对属性或主构造函数参数进行标注时，从相应的 Kotlin 元素生成的 Java 元素会有多个，
 * 因此在生成的 Java 字节码中该注解有多个可能位置。
 *
 * 支持的使用处目标的完整列表为：
 * - file；
 * - property（具有此目标的注解对 Java 不可见）；
 * - field；
 * - get（属性 getter）；
 * - set（属性 setter）；
 * - receiver（扩展函数或属性的接收者参数）；
 * - param（构造函数参数）；
 * - setparam（属性 setter 参数）；
 * - delegate（为委托属性存储其委托实例的字段）
 * */
class Example(@field:Fancy val foo: String,    // 标注 Java 字段
              @get:Fancy val bar: String,      // 标注 Java getter
              @param:Fancy val quux: String)   // 标注 Java 构造函数参数

fun main() {
    Foo().baz(22)
}