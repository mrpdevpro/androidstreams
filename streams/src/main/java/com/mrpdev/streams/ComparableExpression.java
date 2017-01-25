package com.mrpdev.streams;



public interface ComparableExpression<T> {
    boolean evaluate(T left,T right);
}
