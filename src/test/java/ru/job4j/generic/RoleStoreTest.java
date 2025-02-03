package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    public void whenAddAndFindThenUserRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        Role result = roleStore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.add(new Role("1", "USER"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.replace("10", new Role("10", "USER"));
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        roleStore.delete("10");
        Role result = roleStore.findById("1");
        assertThat(result.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        boolean result = roleStore.replace("1", new Role("1", "USER"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        boolean result = roleStore.replace("10", new Role("10", "USER"));
        assertThat(result).isFalse();
    }

}