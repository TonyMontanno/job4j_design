package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void shouldReturnZeroVerticesForSphere() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void shouldReturnFourVerticesForTetrahedron() {
        Box box = new Box(4, 6);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void shouldReturnEightVerticesForCube() {
        Box box = new Box(8, 12);
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
    }

    @Test
    void shouldExistWhenVertexIsZero() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void shouldExistWhenVertexIsPositive() {
        Box box = new Box(8, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void shouldNotExistWhenVertexIsNegative() {
        Box box = new Box(-1, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getAreaForSphere() {
        Box box = new Box(0, 5);
        double edge = 5;
        double expectedArea = 4 * Math.PI * (edge * edge);
        assertThat(box.getArea()).isEqualTo(expectedArea);
    }

    @Test
    void getAreaForTetrahedron() {
        Box box = new Box(4, 6);
        double edge = 6;
        double expectedArea = Math.sqrt(3) * (edge * edge);
        assertThat(box.getArea()).isEqualTo(expectedArea);
    }

    @Test
    void getAreaForCube() {
        Box box = new Box(8, 12);
        double edge = 12;
        double expectedArea = 6 * (edge * edge);
        assertThat(box.getArea()).isEqualTo(expectedArea);
    }
}