package me.marcusslover.caverns.api.utils;

import java.util.List;

public interface IManager<T> {
    default void initialize() {

    }
    void register(T value);
    List<T> getAll();
}
