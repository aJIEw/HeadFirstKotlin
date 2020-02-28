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
        String res = CollectionsKt.joinToString2(arr, "-");
        System.out.println(res);
        String res2 = CollectionsKt.joinToString2(arr, "-", "=>");
        System.out.println(res2);
    }
}
