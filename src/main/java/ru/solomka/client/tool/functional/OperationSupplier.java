package ru.solomka.client.tool.functional;


@FunctionalInterface
public interface OperationSupplier<T> {
    T operate(T join);
}
