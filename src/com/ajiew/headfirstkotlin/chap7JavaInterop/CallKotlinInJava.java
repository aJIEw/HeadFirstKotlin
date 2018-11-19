package com.ajiew.headfirstkotlin.chap7JavaInterop;

/**
 * Java 中调用 Kotlin
 * https://www.kotlincn.net/docs/reference/java-to-kotlin-interop.html
 * */

/**
 * **属性**
 * Kotlin 属性会编译成以下 Java 元素
 * - 一个 getter 方法，名称通过加前缀 get 算出
 * - 一个 setter 方法，名称通过加前缀 set 算出（只适用于 var 属性）
 * - 一个私有字段，与属性名称相同（仅适用于具有幕后字段的属性）
 * */
public class CallKotlinInJava {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 使用了 JvmField 标注的 Kotlin 属性可以直接在 Java 中访问
     * */
    public String getID(Exposed exposed) {
        return exposed.ID;
    }

    public void useFieldInKotlin(Key key1, Key key2) {
        // 1. 通过 JvmStatic 标注的字段
        Key.COMPARATOR.compare(key1, key2);

        // 2. 在命名对象或者伴生对象中的一个延迟初始化的属性具有与属性 setter 相同可见性的静态幕后字段
        Singleton.provider = "";

        // 3. 用 const 标注的（在类中以及在顶层的）属性在 Java 中会成为静态字段
        int VERSION = Key.VERSION;
        int con = Singleton.CONST;
        int max = CallKotlinJvmName.MAX; // Kotlin 文件编译之后会生成文件名作为类名的 Java 类，顶层函数和常量、变量会放到里面
        String someValue = CallKotlinJvmName.getSomeValue(); // 顶层变量会自动生成 get 与 set 方法
    }
}

