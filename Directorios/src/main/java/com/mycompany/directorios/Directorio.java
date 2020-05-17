/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.directorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Belén
 */
public class Directorio {
    int archivos=0;
     private void crearDirectorio(String ruta) {
    File directorio = new File(ruta);
    if (!directorio.exists()) {
        directorio.mkdirs();
    }
}
   private void copiarArchivo(String sOrigen, String sDestino) {
    try {
        File origen = new File(sOrigen);
        File destino = new File(sDestino);
        InputStream in = new FileInputStream(origen);
        OutputStream out = new FileOutputStream(destino);

        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace(System.out);
    } catch (IOException e) {
        e.printStackTrace(System.out);
    }
}
   public int copiarDirectorio(String origen, String destino) {
    crearDirectorio(destino);
    File directorio = new File(origen);
    File f;
    if (directorio.isDirectory()) {
        crearDirectorio(destino);
        String [] files = directorio.list();
        if (files.length > 0) {
            for (String archivo : files) {
                f = new File (origen + File.separator + archivo);
                archivos++;
                if(f.isDirectory()) {
                    crearDirectorio(destino+File.separator+archivo+".backup"+File.separator);
                    copiarDirectorio(origen+File.separator+archivo+File.separator, destino+File.separator+archivo+".backup"+File.separator);
                } else { 
                    copiarArchivo(origen+File.separator+archivo, destino+File.separator+archivo+".backup");
                }
            }
        }
    }
    return archivos;
}  
   
    
}
