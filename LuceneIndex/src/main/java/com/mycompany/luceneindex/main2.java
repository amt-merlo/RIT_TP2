/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luceneindex;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**

/**
 *
 * @author Allison
 */
public class main2 {
    
    public static List sacarTXT(){ //saca las lineas del txt y las guarda en una lista
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    List lista = new ArrayList();
    try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\Users\\gabyg\\Downloads\\wiki-TP2\\wiki-g2.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            //System.out.println(linea);
            lista.add(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    
    return lista; //devuelve un arraylist con las lineas del txt
    } 

    
   
    public static List dividirPaginas(List cadena){  //toma la lista con las lineas del txt y devuelve una lista con las páginas html
        String linea="", pagina="";
        
        int contador = 0, bandera = 0; //contador para saber cantidad de paginas y bandera para saber cuándo agregar la linea al string de la página
        List paginas = new ArrayList(); //arraylist final con el txt separado por páginas
        
        
        //para iterar la lista con las líneas del txt
        Iterator it = cadena.iterator();
        
        while(it.hasNext()){
            linea=it.next().toString(); //combierte el object del arraylist en string 
            
            //buscar la palabra DOCTYPE
            int indiceInicio = linea.indexOf("<!DOCTYPE html");
            int indiceFinal = linea.indexOf("</html>");
            
            if (indiceInicio!=-1){ //si el indice es diferente de -1 entonces encontró la cadena <!DOCTYPE html donde la nueva página inicia
                
                contador=contador+1; //para saber cuántas páginas hay
                bandera=1; //para que agrege la linea al String de la página
                
            }else if(indiceFinal!=-1){ //si el indice es diferente de -1 entonces encontró el final de la página
                
                pagina = pagina+linea+"\n"; //agrega la última linea
                bandera=0; //para que agregue la página a la lista y se reinicie
            }
      
            //si la bandera está en 1 agrega la línea al String de la página, si está en 0 agrega el String a la lista y lo vacía 
            if (bandera == 1){
                pagina = pagina+linea+"\n";
            }else{
                paginas.add(pagina);
                pagina = "";
            }
            
        }
        System.out.println(contador);
        
        return paginas;
    }
    

    
     public static void main(String[] args) {
        List cadena = sacarTXT(); //devuelve una lista con las lineas del txt
        List paginas = dividirPaginas(cadena); //devuelve una lista con las paginas
        System.out.println(paginas.get(0));
     }
}

