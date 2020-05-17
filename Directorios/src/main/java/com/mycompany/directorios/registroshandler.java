/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.directorios;

/**
 *
 * @author Belén
 */

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class registroshandler extends DefaultHandler { 
        boolean dateRep = false;
	boolean cases = false;
	boolean countriesAndTerritories = false;
        boolean deaths =false;
        boolean continentExp=false;
        Registros reg = new Registros();
        List<Registros> lista = new ArrayList<>();
    
        @Override
	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("DATEREP")) {
			dateRep = true;
		}

		if (qName.equalsIgnoreCase("CASES")) {
			cases = true;
		}

		if (qName.equalsIgnoreCase("countriesAndTerritories")) {
			countriesAndTerritories = true;
		}
                if (qName.equalsIgnoreCase("deaths")) {
			deaths = true;
		}
                if (qName.equalsIgnoreCase("continentExp")) {
			continentExp = true;
		}
	}
        @Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (dateRep) {
			reg.setDateRep(new String(ch, start, length));
			dateRep = false;
                        
		}

		if (cases) {
			reg.setCases(Integer.parseInt(new String(ch, start, length)));
			cases = false;
		}

		if (countriesAndTerritories) {
			reg.setCountriesAndTerritories(new String(ch, start, length));
			countriesAndTerritories = false;
		}
                if (deaths) {
			reg.setDeaths(Integer.parseInt(new String(ch, start, length)));
			deaths = false;
		}
                if (continentExp) {
			reg.setContinentExp(new String(ch, start, length));
			continentExp = false;
		}
	}
        @Override
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
                if (qName.equals("record")) {
            lista.add(reg);
            reg = new Registros();

	}
        }
        public List<Registros> getLista(){
        return lista;
        }

}