package com.innovandoapps.library.kernel.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Clase con funciones utilitarias de fecha
 * @author Marcos Ramirez
 */
public class FechaUtil {

    /**
     * Retorna una cadena de fecha actual con formato yyyy-MM-dd HH:mm:ss.SSS
     * @return String de fecha
     */
    public static String getFechaActual(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    /**
     * Retorna una cadena de fecha actual con formato yyyy-MM-dd
     * @return String de fecha
     */
    public static String getFechaDia(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * Retorna una cadena de fecha actual con formato yyyy-MM-dd:HH:mm:ss
     * @return String de fecha
     */
    public static String getFechaCadena(){
        return new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
    }

    public static String getFechaCadena2(){
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
    }

    /**
     * Retorna cadena fecha con formato yyyy-MM-dd HH:mm:ss.SSS a partir de milisegundos
     * @param time tiempo en milisegundos a convertir a fecha
     * @return String de fecha
     */
    public static String milisegunToDate(long time){
        Date date=new Date(time);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
    }

    /**
     * Retorna cadena de Hora en formato HH:mm:ss a partir de milisegundos
     * @param time tiempo en milisegundos a convertir en hora
     * @return String de hora
     */
    public static String SegundoToHora(long time){
        long milisegundos = time*1000;
        long diffsegundos = milisegundos / 1000 % 60;
        long diffminutos = milisegundos / (60 * 1000) % 60;
        long diffHoras = milisegundos / (60 * 60 * 1000) % 24;

        DecimalFormat df = new DecimalFormat("00");
        return String.format("%s:%s:%s",df.format(diffHoras),df.format(diffminutos),df.format(diffsegundos));
    }

    public static String getHoraActual(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
