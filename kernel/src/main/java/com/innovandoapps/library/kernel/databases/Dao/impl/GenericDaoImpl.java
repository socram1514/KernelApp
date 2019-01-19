package com.innovandoapps.library.kernel.databases.Dao.impl;

import android.content.ContentValues;

import com.innovandoapps.library.kernel.databases.Dao.GenericDao;
import com.innovandoapps.library.kernel.databases.DbBaseManager;

import java.lang.reflect.Field;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    protected abstract DbBaseManager geDbBaseManager();
    protected abstract String getTableName();

    @Override
    public Long insert(T obj) {
        DbBaseManager data = geDbBaseManager();
        ContentValues contentValues = new ContentValues();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if( value != null){
                    if(value instanceof String){
                        contentValues.put(field.getName(),(String)value);
                    }
                    if(value instanceof Integer){
                        contentValues.put(field.getName(),(Integer)value);
                    }
                    if(value instanceof Long){
                        contentValues.put(field.getName(),(Long)value);
                    }
                    if(value instanceof Double){
                        contentValues.put(field.getName(),(Double)value);
                    }
                    if(value instanceof Float){
                        contentValues.put(field.getName(),(Float)value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data.insertarResgistro(getTableName(),contentValues);
    }

    @Override
    public Integer update(T obj) {
        return null;
    }

    @Override
    public T find(Long id) {
        return null;
    }

    @Override
    public List<T> getList() {
        return null;
    }

    @Override
    public T delete(T obj) {
        return null;
    }




}
