package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean hasNextElement = false;
        while (row < data.length) {
            if (column < data[row].length) {
                hasNextElement = true;
                break;
            }
            row++;
            column = 0;
        }
        return hasNextElement;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer value = data[row][column];
        column++;
        return value;
    }
}