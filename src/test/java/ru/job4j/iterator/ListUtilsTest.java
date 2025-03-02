package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> isEven = num -> num % 2 == 0;

        ListUtils.removeIf(input, isEven);

        List<Integer> expected = Arrays.asList(1, 3, 5);
        assertThat(input).containsExactlyElementsOf(expected);
    }

    @Test
    void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> isEven = num -> num % 2 == 0;

        ListUtils.replaceIf(input, isEven, 0);

        List<Integer> excepted = Arrays.asList(1, 0, 3, 0, 5);
        assertThat(input).containsExactlyElementsOf(excepted);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        ListUtils.removeAll(list1, list2);

        List<Integer> excepted = Collections.emptyList();
        assertThat(list1).containsExactlyElementsOf(excepted);
    }
}