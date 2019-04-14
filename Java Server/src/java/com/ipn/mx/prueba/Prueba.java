
package com.ipn.mx.prueba;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author andres
 */
public class Prueba {
    public static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    
    public Prueba(){
        List<Dato> dato = new ArrayList<>();
        dato.add( new Dato("caso1", "Java"));
        dato.add( new Dato("caso2", "Android"));
        dato.add( new Dato("caso3", "Java"));
        dato.add( new Dato("caso4", "Android"));
        dato.add( new Dato("caso5", "Other"));
        System.out.println(
            dato.stream()
                    .filter( d -> d.getCategoria().equals("Java"))
                    .collect(Collectors.toList()));
    }
    
    public class Dato{
        String nombre;
        String categoria;
        
        Dato(String nombre, String categoria){
            this.nombre = nombre;
            this.categoria = categoria;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
        
        
        @Override
        public String toString(){
            return "nombre: "+nombre+"\tcategoria: "+categoria;
        }
    }
    
    public static void main(String[] args) {
        Date date = new Date("2019/08/10");
        java.sql.Date sql = new java.sql.Date(date.getTime());
        System.out.println(date);
        System.out.println( new Timestamp(date.getTime()));
        System.out.println("===============>");
        Prueba p = new Prueba();
    }    
}
