package test.datastructure;

import java.util.*;

public class TreeSetTest {
    public static void main(String[] args) {

        TreeSet<Integer> integers = new TreeSet<>();

        integers.add(5);
        integers.add(1);
        integers.add(3);
        integers.add(4);
        integers.add(2);

        for (int value:
             integers) {
            System.out.println(value);
        }


    }
}
