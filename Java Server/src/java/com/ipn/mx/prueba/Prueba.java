
package com.ipn.mx.prueba;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 *
 * @author andres
 */
public class Prueba {
    public static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String[] args) {
        Date date = new Date("2019/08/10");
        java.sql.Date sql = new java.sql.Date(date.getTime());
        System.out.println(date);
        System.out.println( new Timestamp(date.getTime()));
    }    
}
