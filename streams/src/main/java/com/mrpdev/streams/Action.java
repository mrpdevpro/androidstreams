package com.mrpdev.streams;



public interface Action<T> {
    void apply(T t);
}
