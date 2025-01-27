package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean hasNextElement = false;
        while (index < data.length) {
            if (data[index] != null) {
                hasNextElement = true;
                break;
            }
            index++;
        }
        return hasNextElement;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];

    }

}