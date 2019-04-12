
package com.ipn.mx.prueba;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author andres
 */
public class Prueba {
    public static void main(String[] args) {
        Date date = new Date();
        java.sql.Date sql = new java.sql.Date(date.getTime());
        System.out.println(date);
        System.out.println( new Timestamp(date.getTime()));
    }    
}
