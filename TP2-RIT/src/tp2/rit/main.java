/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.rit;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Allison
 */


public class main {
    
public static List sacarTXT(){
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    List lista = new ArrayList();
    try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\Users\\Allison\\Downloads\\wiki-p2.txt");
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
    
    return lista;
    } 



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        List cadena = sacarTXT();
        System.out.println(cadena);
      
      
      
    }
    
}
