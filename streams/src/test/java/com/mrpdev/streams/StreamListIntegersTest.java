package com.mrpdev.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.mrpdev.streams.StreamMethods.stream;
import static org.junit.Assert.assertTrue;


public class StreamListIntegersTest {
    List<Integer> testListIntegers = Arrays.asList(2, 1, 3, 4, 10);

    @Test
    public void test_Integers_filter_basic() throws Exception {
        List<Integer> list = stream(testListIntegers).filter(integer -> integer > 1).toList();
        assertTrue(list.size() == 4);
    }

    @Test
    public void test_Integers_first_basic() throws Exception {
        Integer result = stream(testListIntegers).first();
        assertTrue(result == 2);
    }

    @Test
    public void test_Integers_last_basic() throws Exception {
        Integer result = stream(testListIntegers).last();
        assertTrue(result == 10);
    }

    @Test
    public void test_Integers_all_basic() throws Exception {
        boolean result = stream(testListIntegers).all(integer -> integer > 0);
        assertTrue(result);
    }

    @Test
    public void test_Integers_any_basic() throws Exception {
        boolean result = stream(testListIntegers).any(integer -> integer == 10);
        assertTrue(result);
    }

    @Test
    public void test_Integers_sumInt_basic() throws Exception {
        int result = stream(testListIntegers).sumOfInt(integer -> integer);
        assertTrue(result == 20);
    }

    @Test
    public void test_Integers_orderBy_basic() throws Exception {
        List<Integer> result = stream(testListIntegers).orderByInteger(integer -> integer).toList();
        assertTrue(result.get(0) == 1);
    }

    @Test
    public void test_Integers_map_basic() throws Exception {
        List<String> result = stream(testListIntegers).map(integer -> "val:" + String.valueOf(integer)).toList();
        assertTrue(result.size() == testListIntegers.size());
        assertTrue(result.get(0).equals("val:2"));
    }

    @Test
    public void test_Integers_skip_basic() throws Exception {
        List<Integer> result = stream(testListIntegers).skip(2);
        assertTrue(result.size() == 3);
    }

    @Test
    public void test_Integers_max_basic() throws Exception {
        int result = stream(testListIntegers).max((left, right) -> left > right);
        assertTrue(result == 10);
    }

    @Test
    public void test_Integers_mapToString_filter() throws Exception {
        List<String> result = stream(testListIntegers)
                .map(integer -> "val:" + String.valueOf(integer))
                .filter(s -> s.endsWith("0"))
                .toList();
        assertTrue(result.size() == 1);
    }

    @Test
    public void test_Integers_mapToString_filter_count() throws Exception {
        int result = stream(testListIntegers)
                .map(integer -> "val:" + String.valueOf(integer))
                .filter(s -> s.endsWith("0"))
                .count();
        assertTrue(result == 1);
    }

    @Test
    public void test_Integers_filter_mapToString_filter() throws Exception {
        List<String> result = stream(testListIntegers)
                .filter(integer -> integer < 2)
                .map(integer -> "val:" + String.valueOf(integer))
                .filter(s -> s.endsWith("0"))
                .toList();
        assertTrue(result.size() == 0);
    }
}