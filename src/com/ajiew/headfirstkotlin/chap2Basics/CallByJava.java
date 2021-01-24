package com.ajiew.headfirstkotlin.chap2Basics;

import java.util.ArrayList;
import java.util.List;

public class CallByJava {

    public static void main(String[] args) {
        testJvmOverloads();
    }

    static void testJvmOverloads() {
        List<Integer> arr = new ArrayList<Integer>() {{
            add(2);
            add(4);
            add(0);
        }};
        // 没有 @JvmOverloads 注解的方法，不能使用默认参数
        String res = CollectionsKt.joinToString(arr, "-", "pre-", "-post");
        System.out.println(res);
        // 使用 @JvmOverloads 后会为我们自动生成对应的重载方法，所以可以在 Java 中调用
        String res2 = CollectionsKt.joinToString2(arr, "-", "=>");
        System.out.println(res2);
    }
}
