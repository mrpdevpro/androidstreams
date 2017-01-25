package com.mrpdev.streams;

import java.util.List;
import java.util.Set;



public final class StreamMethods {

    public  static <T> Stream<T> stream(List<T> list ){
        return new StreamList<T>(list);
    }

    public  static <T> StreamSet<T> stream(Set<T> set ){
        return new StreamSet(set);
    }

    public static <T> ObjectStream<T> instance(T instance){return new ObjectStream<T>(instance);}

    private StreamMethods(){

    }
}
