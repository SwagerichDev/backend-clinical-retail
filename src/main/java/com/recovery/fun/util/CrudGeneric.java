package com.recovery.fun.util;

import java.util.List;

public interface CrudGeneric <T>{
    T save(T t);
//    T update(T t);
    void delete(T t);
    T findById(Long id);
    List<T> findAll();
}
