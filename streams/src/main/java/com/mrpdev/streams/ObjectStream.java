package com.mrpdev.streams;



public class ObjectStream<T> {
    private T intance;

    public ObjectStream(T t) {
        this.intance = t;
    }

    public ObjectStream<T> when(Predicate<T> predicate,Action<T> action) {
        if(intance!=null) {
            if(predicate.evaluate(intance))
                action.apply(intance);
        }
        return this;
    }
    public ObjectStream<T> when(Predicate<T> predicate,Action<T> actionTrue,Action<T> actionFalse) {
        if(intance!=null) {
            if(predicate.evaluate(intance))
                actionTrue.apply(intance);
            else
                actionFalse.apply(intance);
        }
        return this;
    }
    public ObjectStream<T> onNotNull(Action<T> action) {
        if(intance!=null) {
            action.apply(intance);
        }
        return this;
    }
    public ObjectStream<T> onNull(ActionParameterless action) {
        if(intance!=null)
            action.apply();
        return this;
    }
}
