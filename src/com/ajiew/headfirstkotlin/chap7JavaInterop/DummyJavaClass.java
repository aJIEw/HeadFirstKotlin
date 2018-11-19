package com.ajiew.headfirstkotlin.chap7JavaInterop;

public class DummyJavaClass {

    public String name = "Aaron";

    public boolean is() {
        System.out.println("Successfully called!");
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
