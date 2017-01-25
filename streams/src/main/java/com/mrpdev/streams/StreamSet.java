package com.mrpdev.streams;

import java.util.HashSet;
import java.util.Set;



public class StreamSet<T> {
    private Set<T> list;

    public StreamSet(Set<T> list){
        this.list = list;
    }

    public StreamSet<T> where(Predicate<T> function){
        Set<T> filteredList = new HashSet<T>(0);
        for(T ret:list){
            if(function.evaluate(ret)){
                filteredList.add(ret);
            }
        }
        this.list = filteredList;
        return this;
    }
    public <Project> StreamSet<Project> select(Function<T,Project> function){
        Set<Project> projectList = new HashSet<Project>(list.size());
        for(T item:list){
            projectList.add(function.evaluate(item));
        }
        return new StreamSet(projectList);
    }
    public Set<T> toSet(){
        return list;
    }

    public T first(){
        if(list.size()>0){
            return list.iterator().next();
        }else {
            return null;
        }
    }

    public int count(){
        return list.size();
    }


}
