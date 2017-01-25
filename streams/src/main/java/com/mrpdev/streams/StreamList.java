package com.mrpdev.streams;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class StreamList<T> implements Stream<T> {
    private List<T> list;

    public StreamList(List<T> list) {
        this.list = list;
    }

    @Override
    public Stream<T> filter(Predicate<T> function) {
        List<T> filteredList = new ArrayList<T>(0);
        for (T ret : list) {
            if (function.evaluate(ret)) {
                filteredList.add(ret);
            }
        }
        this.list = filteredList;
        return this;
    }

    @Override
    public <P> Stream<P> map(Function<T, P> function) {
        List<P> projectList = new ArrayList<P>(list.size());
        for (T item : list) {
            projectList.add(function.evaluate(item));
        }
        return new StreamList(projectList);
    }

    @Override
    public <C> Stream<C> cast() {
        List<C> castList = new ArrayList<>(list.size());
        for (T item : list) {
            castList.add((C) item);
        }
        return new StreamList<C>(castList);
    }




    @Override
    public Stream<T> orderBy(ComparableExpression<T> comparator) {
        Collections.sort(list, (left, right) -> {
            if (comparator.evaluate(left, right))
                return 1;
            else if (comparator.evaluate(right, left))
                return -1;
            else
                return 0;
        });
        return this;
    }

    @Override
    public Stream<T> orderByDesc(ComparableExpression<T> comparator) {
        Collections.sort(list, (left, right) -> {
            if (comparator.evaluate(left, right))
                return -1;
            else if (comparator.evaluate(right, left))
                return 1;
            else
                return 0;
        });
        return this;
    }

    @Override
    public Stream<T> orderByInteger(Function<T, Integer> selector) {
        Collections.sort(list, (left, right) -> {
            if (selector.evaluate(left)>selector.evaluate(right))
                return 1;
            else if (selector.evaluate(left)<selector.evaluate(right))
                return -1;
            else
                return 0;
        });
        return this;
    }
    @Override
    public Stream<T> orderByString(Function<T, String> selector) {
        Collections.sort(list, (left, right) ->
             selector.evaluate(left).compareTo(selector.evaluate(right))
        );
        return this;
    }

    @Override
    public void forEach(Function<T,T> action) {
        List<T> alterList =  new ArrayList<>( list.size());

        for (T item : list) {
            alterList.add( action.evaluate(item) );
        }
        list = alterList;
    }

    @Override
    public List<T> toList() {
        return list;
    }

    @Override
    public ArrayList<T> toArrayList() {
        return (ArrayList<T>) list;
    }

    @Override
    @Nullable
    public T first() {
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Nullable
    public T last() {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public int count() {
        return list.size();
    }

    @Override
    public <C> List<C> castList() throws StreamException {
        List<C> castList = new ArrayList<>(list.size());
        for (T item : list) {
            try {
                castList.add((C) item);
            } catch (ClassCastException e) {
                throw new StreamException("Invalid.Cast");
            }
        }
        return castList;
    }

    @Override
    public boolean any(Predicate<T> predicate) {
        for (T item : list) {
            if (predicate.evaluate(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean all(Predicate<T> predicate) {
        for (T item : list) {
            if (!predicate.evaluate(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Nullable
    public List<T> skip(int positions) {
        if (positions >= list.size())
            return Collections.emptyList();
        else {
            return list.subList(positions, list.size());
        }
    }

    @Override
    public int sumOfInt(Function<T, Integer> selector) {
        int sum = 0;
        for (T item : list) {
            sum += selector.evaluate(item);
        }
        return sum;
    }

    @Override
    public long sumOfLong(Function<T, Long> selector) {
        Long sum = 0L;
        for (T item : list) {
            sum += selector.evaluate(item);
        }
        return sum;
    }

    @Override
    public double sumOfDouble(Function<T, Double> selector) {
        Double sum = 0d;
        for (T item : list) {
            sum += selector.evaluate(item);
        }
        return sum;
    }

    @Override
    public T max(ComparableExpression<T> comparator) {
        T max = list.get(0);
        for (T item : list) {
            if (comparator.evaluate(item, max))
                max = item;
        }
        return max;
    }

    @Override
    public T min(ComparableExpression<T> comparator) {
        T min = list.get(0);
        for (T item : list) {
            if (!comparator.evaluate(item, min))
                min = item;
        }
        return min;
    }
}
