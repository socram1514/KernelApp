package com.innovandoapps.library.kernel.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public abstract  class BasicFuntionsDb {

    protected SQLiteDatabase db;

    public abstract SQLiteDatabase getDbReadWrite();

    public abstract SQLiteDatabase getDbReadOnly();

    public long insertarResgistro(String tabla,ContentValues registros){
        long result = 0;
        db = getDbReadWrite();
        db.beginTransaction();
        try{
            result = db.insert(tabla,null,registros);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            result = -1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return result;
    }

    public boolean actualizarRegistro(String tabla,ContentValues registros,String clausuraWhere,String[] arg){
        boolean sw = false;
        db = getDbReadWrite();
        db.beginTransaction();
        try{
            db.update(tabla, registros, clausuraWhere, arg);
            db.setTransactionSuccessful();
            sw=true;
        }catch(SQLiteException ex){

        }finally{
            db.endTransaction();
        }
        db.close();
        return sw;
    }

    public int updateRegistros(String tabla,ContentValues registros,String clausuraWhere,String[] arg){
        int cont = 0;
        db = getDbReadWrite();
        db.beginTransaction();
        try{
            cont = db.update(tabla, registros, clausuraWhere, arg);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            cont = -1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return cont;
    }

    public Cursor consultar(String tabla, String[] campos, String clausuraWhere, String[] arg, String Group, String clausuraHaving, String order){
        Cursor cursor = null;
        db = getDbReadOnly();
        try{
            cursor = db.query(tabla,campos,clausuraWhere,arg,Group,clausuraHaving,order);
        }catch(SQLiteException ex){

        }
        return cursor;
    }

    public Cursor consultaSql(String sql,String[] args){
        Cursor cursor = null;
        db = getDbReadOnly();
        try{
            cursor = db.rawQuery(sql,args);
        }catch(SQLiteException ex){

        }
        return cursor;
    }

    public int eliminarRegistros(String tabla,String clausuraWhere,String[] args ){
        int delete = 0;
        db = getDbReadWrite();
        db.beginTransaction();
        try{
            delete = db.delete(tabla, clausuraWhere, args);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            delete = -1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return delete;
    }

    public boolean sqlQuery(String sql){
        db = getDbReadWrite();
        db.beginTransaction();
        db.execSQL(sql);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return true;
    }

    /**
     * Cierra la BD
     */
    public void close(){
        db.close();
    }
}
