package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {

        for (int i = 0; i < nodes.size(); i++) {
            while (source.hasNext()) {
                nodes.get(i).add(source.next());
                i = (i + 1) % nodes.size();

            }
        }
    }
}