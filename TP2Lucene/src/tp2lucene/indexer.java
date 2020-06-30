/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2lucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
/**
 *
 * @author Allison
 */
public class indexer {
    
    
   
    public IndexWriter crearIndice(){
    
        String indexPath = "C:\\Users\\Allison\\Desktop\\indexerPrueba\\indice";

        boolean create = true;



        Date start = new Date();
        try {
          System.out.println("Indexing to directory '" + indexPath + "'...");

          Directory dir = FSDirectory.open(Paths.get(indexPath));
          Analyzer analyzer = new StandardAnalyzer();
          IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

          if (create) {
              
            // Create a new index in the directory, removing any
            // previously indexed documents:
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
          } else {
            // Add new documents to an existing index:
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
          }

          // Optional: for better indexing performance, if you
          // are indexing many documents, increase the RAM
          // buffer.  But if you do this, increase the max heap
          // size to the JVM (eg add -Xmx512m or -Xmx1g):
          //
          // iwc.setRAMBufferSizeMB(256.0);

          IndexWriter writer = new IndexWriter(dir, iwc);
          Date end = new Date();
          System.out.println(end.getTime() - start.getTime() + " total milliseconds");
          
          return writer;

          // NOTE: if you want to maximize search performance,
          // you can optionally call forceMerge here.  This can be
          // a terribly costly operation, so generally it's only
          // worth it when your index is relatively static (ie
          // you're done adding documents to it):
          //
          // writer.forceMerge(1);

        } catch (IOException e) {
          System.out.println(" caught a " + e.getClass() +
           "\n with message: " + e.getMessage());
        }

        return null;
    } 
    
    
    
    public void indexHTML(ArrayList lista, IndexWriter writer) throws IOException{
        // make a new, empty document
      Document doc = new Document();
      List encabezados = (List) lista.get(1);
      List referencias = (List) lista.get(2);
      List texto = (List) lista.get(3);
      
     
      //Agrega el titulo del HTML
      doc.add(new StringField("titulo", (String) lista.get(0),Field.Store.YES));
      
      //Agrega los encabezados del HTML
      doc.add(new StringField("encab",  encabezados.toString(),Field.Store.YES));
      
      //Agrega las referencias del HTML
      doc.add(new StringField("ref", referencias.toString(),Field.Store.YES));
      
      //Agrega todo el texto de body
      doc.add(new TextField("texto", texto.toString(), Field.Store.YES));
      
      
     
      
      
      if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) { //en el main cambiar la configuracion a create or append para actualizar el indice
        // New index, so we just add the document (no old document can be there):
        
        writer.addDocument(doc);
        
      } else {
       // writer.updateDocument(new Term("path", file.toString()), doc);
          System.out.println("Sorry");
       
      }
      
    
    }
}
