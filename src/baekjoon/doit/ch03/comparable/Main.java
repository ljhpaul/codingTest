package baekjoon.doit.ch03.comparable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Score> arr = new ArrayList<>();
        arr.add(new Score(80, 100));
        arr.add(new Score(100, 50));
        arr.add(new Score(70, 100));
        arr.add(new Score(80, 90));

        Collections.sort(arr);
        for(Score s : arr) {
            System.out.println(s);
        }
    }
}
