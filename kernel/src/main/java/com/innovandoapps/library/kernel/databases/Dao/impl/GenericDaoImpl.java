package com.innovandoapps.library.kernel.databases.Dao.impl;

import android.content.ContentValues;
import com.innovandoapps.library.kernel.databases.Dao.GenericDao;
import com.innovandoapps.library.kernel.databases.DbBaseManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    protected abstract DbBaseManager geDbBaseManager();
    protected abstract String getTableName();

    @Override
    public Long insert(T obj) {
        DbBaseManager data = geDbBaseManager();
        ContentValues contentValues = buildContentValues(obj);
        return data.insertarResgistro(getTableName(),contentValues);
    }

    @Override
    public Integer update(T obj,long id) {
        DbBaseManager data = geDbBaseManager();
        ContentValues contentValues = buildContentValues(obj);
        return data.updateRegistros(getTableName(),contentValues,"_id=?",new String[]{Long.toString(id)});
    }

    @Override
    public T delete(T obj) {
        DbBaseManager data = geDbBaseManager();
        data.eliminarRegistros(getTableName(),generateWhere(obj),generateArrayObjet(obj));
        return obj;
    }

    protected ContentValues buildContentValues(T obj){
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
        return contentValues;
    }

    protected String generateWhere(T obj){
        String where = "";
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if( value != null){
                    where = where + field.getName() + "=?, ";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        where = where.substring(0, where.length() - 2) + ");";
        return where;
    }

    protected String[] generateArrayObjet(T obj){
        List<String> array = new ArrayList<>();
        int cont = 0;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if( value != null){
                    cont++;
                    if(value instanceof String){
                        array.add((String)value);
                    }
                    if(value instanceof Integer){
                        Integer i = (Integer)value;
                        array.add(Integer.toString(i));
                    }
                    if(value instanceof Long){
                        Long l = (Long)value;
                        array.add(Long.toString(l));
                    }
                    if(value instanceof Double){
                        Double d = (Double)value;
                        array.add(Double.toString(d));
                    }
                    if(value instanceof Float){
                        Float f = (Float)value;
                        array.add(Float.toString(f));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String[] stockArr = new String[array.size()];
        return array.toArray(stockArr);
    }
}
