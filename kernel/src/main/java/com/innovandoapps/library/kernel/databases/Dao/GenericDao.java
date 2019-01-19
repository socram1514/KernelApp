package com.innovandoapps.library.kernel.databases.Dao;

import java.util.List;

public interface GenericDao<T> {

    Long insert(T obj);

    Integer update(T obj,long id);

    T find(Long id);

    List<T> getList();

    T delete(T obj);
}
