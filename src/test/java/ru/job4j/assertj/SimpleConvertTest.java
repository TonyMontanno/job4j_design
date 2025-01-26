package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(list).hasSize(5)
                .isNotEmpty()
                .allMatch(s -> s.matches("^[a-z]+$"))
                .contains("two")
                .contains("one", Index.atIndex(0))
                .containsAnyOf("zero", "second", "five")
                .doesNotContain("second", Index.atIndex(1))
                .containsOnly("five", "one", "two", "three", "four")
                .containsExactly("one", "two", "three", "four", "five")
                .doesNotContain("six")
                .startsWith("one")
                .endsWith("four", "five")
                .containsSequence("two", "three")
                .allMatch(s -> s.length() > 2)
                .anyMatch(s -> s.startsWith("fi"))
                .noneMatch(s -> s.endsWith("te"))
                .filteredOn(s -> s.equals("five")).hasSize(1);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "two", "three", "four", "five");
        assertThat(set).hasSize(5)
                .isNotEmpty()
                .contains("two")
                .doesNotContain("six")
                .containsAnyOf("zero", "second", "five")
                .containsOnly("five", "one", "two", "three", "four")
                .containsSequence("two", "three")
                .allMatch(s -> s.length() > 2)
                .anyMatch(s -> s.startsWith("fi"))
                .noneMatch(s -> s.endsWith("te"))
                .filteredOn(s -> s.equals("five")).hasSize(1);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three", "four", "five");

        assertThat(map).hasSize(5)
                .isNotEmpty()
                .contains(
                        entry("one", 0),
                        entry("two", 1))
                .doesNotContain(entry("ten", 0))
                .containsAnyOf(
                        entry("four", 3),
                        entry("ten", 10),
                        entry("hundred", 5)
                )
                .containsOnly(
                        entry("one", 0),
                        entry("two", 1),
                        entry("three", 2),
                        entry("four", 3),
                        entry("five", 4)
                )
                .containsKey("one")
                .containsValue(0)
                .doesNotContainValue(7)
                .doesNotContainKey("ten");
    }
}