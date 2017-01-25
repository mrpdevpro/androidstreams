package com.mrpdev.streams;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public interface Stream<T> {
    Stream<T> filter(Predicate<T> function);

    <P> Stream<P> map(Function<T, P> function);

    <C> Stream<C> cast();


    Stream<T> orderBy(ComparableExpression<T> comparator);

    Stream<T> orderByDesc(ComparableExpression<T> comparator);

    Stream<T> orderByInteger(Function<T, Integer> comparator);

    Stream<T> orderByString(Function<T, String> comparator);

    void forEach(Function<T,T> action);

    List<T> toList();

    ArrayList<T> toArrayList();

    @Nullable
    T first();

    @Nullable
    T last();

    int count();

    <C> List<C> castList() throws StreamException;

    boolean any(Predicate<T> predicate);

    boolean all(Predicate<T> predicate);

    @Nullable
    List<T> skip(int positions);

    int sumOfInt(Function<T,Integer> selector);

    long sumOfLong(Function<T,Long> selector);

    double sumOfDouble(Function<T,Double> selector);

    T max(ComparableExpression<T> comparator);

    T min(ComparableExpression<T> comparator);
}
