package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {

        boolean result = false;

        if (findBy(child).isEmpty()) {
            Optional<Node<E>> parentOptional = findBy(parent);

            if (parentOptional.isPresent()) {
                Node<E> childNode = new Node<>(child);
                parentOptional.get().children.add(childNode);
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> node.value.equals(value));
    }

    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            if (condition.test(node)) {
                return Optional.of(node);
            }
            data.addAll(node.children);
        }
        return Optional.empty();
    }
}