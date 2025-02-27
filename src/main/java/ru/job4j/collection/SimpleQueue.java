package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int inputSize = 0;
    private int outputSize = 0;

    public T poll() {
        if (outputSize == 0) {
            int count = inputSize;
            for (int i = 0; i < count; i++) {
                T element = input.pop();
                output.push(element);
                outputSize++;
            }
            inputSize = 0;
        }
        if (outputSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        outputSize--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inputSize++;
    }
}