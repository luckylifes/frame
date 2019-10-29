package com.dj.cn;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TestStream {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        String mergedString = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(mergedString);


    }
}
