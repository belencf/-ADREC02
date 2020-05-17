/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.directorios;
import com.google.gson.*;
import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Belén
 */
public class Main {
  public static void main(String[] args) {
          
       Gson gson = new Gson();
       try{
            FileReader fr = new FileReader("config.json");
            Ruta rutas =gson.fromJson(fr,Ruta.class);
            long inicio = System.currentTimeMillis();
            File entrada = new File(rutas.directorio_orixe);
                if (entrada.exists()){
                    Directorio dir=new Directorio();
                    int archivos = dir.copiarDirectorio(rutas.directorio_orixe,rutas.directorio_backup);
                    long fin = System.currentTimeMillis();         
                    double segundos = (double) ((fin - inicio)/1000);
                    Salida salida =new Salida();
                    salida.numero_de_arquivos_copiados=archivos;
                    salida.tempo_utilizado_en_segundos=segundos;
                    Writer writer = new FileWriter("informe.json");
                    gson = new GsonBuilder().setPrettyPrinting().create();
                    writer.write(gson.toJson(salida));
                    writer.close();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch(IOException e){
            e.printStackTrace(System.out);
        }
       try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
        registroshandler handler =new registroshandler();
        saxParser.parse("coronavirus.xml", handler);
        List<Registros> lista = handler.getLista();
        String fecha="05/05/2020";
        Continente Asia=new Continente("Asia");
        Continente America=new Continente("America");
        Continente Europe=new Continente("Europe");
        Continente Africa=new Continente("Africa");
        Continente Oceania=new Continente("Oceania");
        for  (Registros reg:lista){
            if (reg.getDateRep().equals(fecha)){
                System.out.println(reg.getCountriesAndTerritories()+" "+ reg.getCases());
                switch (reg.getContinentExp()){
                    case "Asia":   Asia.numero_falecementos=Asia.numero_falecementos+reg.getDeaths();
                                   Asia.numero_casos=Asia.numero_casos+reg.getCases();
                                   break;
                    case "Europe": Europe.numero_falecementos=Europe.numero_falecementos+reg.getDeaths();
                                   Europe.numero_casos=Europe.numero_casos+reg.getCases();
                                   break;
                    case "Africa": Africa.numero_falecementos=Africa.numero_falecementos+reg.getDeaths();
                                   Africa.numero_casos=Africa.numero_casos+reg.getCases();
                                   break;
                    case "Oceania":Oceania.numero_falecementos=Oceania.numero_falecementos+reg.getDeaths();
                                   Oceania.numero_casos=Oceania.numero_casos+reg.getCases();
                                   break;
                    case "America":America.numero_falecementos=America.numero_falecementos+reg.getDeaths();
                                   America.numero_casos=America.numero_casos+reg.getCases();
                                   break;                
                }
            }
        }
        @SuppressWarnings("unchecked")
        List<Continente> continentes = new ArrayList<>();
        continentes.add(Asia);
        continentes.add(America);
        continentes.add(Africa);
        continentes.add(Europe);
        continentes.add(Oceania);    
        ListaContinentes output =new ListaContinentes();
        output.Continentes=continentes;
        gson = new GsonBuilder().setPrettyPrinting().create();  
        String fichero = gson.toJson(output);  
        Writer writer = new FileWriter("coronavirus.json");
        writer.write(fichero);
        writer.close();
        
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch(IOException e){
            e.printStackTrace(System.out);
        } catch(SAXException e){
            e.printStackTrace(System.out);
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
       
    }  
}
