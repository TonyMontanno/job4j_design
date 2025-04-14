package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentsAndEmptyLines() {
        String path = "./data/pair_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Anton Yun");
        assertThat(config.value("age")).isEqualTo("35");
    }

    @Test
    void whenKeyValueWithDoubleEqualSign() {
        String path = "./data/pair_with_double_equals_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("ключА")).isEqualTo("значение=1");
        assertThat(config.value("ключБ")).isEqualTo("значение=");
    }

    @Test
    void whenEmptyKey() {
        String path = "./data/empty_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenEmptyValue() {
        String path = "./data/empty_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenMissingEqualSign() {
        String path = "./data/missing_equal_sign.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenEmptyKeyAndValue() {
        String path = "./data/empty_key_and_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}