package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void shouldExceptionWhenNameHasNoSpecialSymbol() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(invalidName))
                .hasMessageMatching("^.+")
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(invalidName)
                .hasMessageContaining("does not contain the symbol '='");
    }

    @Test
    void shouldExceptionWhenNameStartWithSpecialSymbol() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "=key:value";
        assertThatThrownBy(() -> nameLoad.parse(invalidName))
                .hasMessageMatching("^.+")
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(invalidName)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void shouldExceptionWhenNameEndWithSpecialSymbol() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "key:value=";
        assertThatThrownBy(() -> nameLoad.parse(invalidName))
                .hasMessageMatching("^.+")
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(invalidName)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void shouldExceptionWhenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .hasMessageMatching("^.+")
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
}