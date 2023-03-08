package com.icssociety.automatedrequests;

import java.util.*;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarHeader;

public class SetResponseHeaders {
    public static void save(Har har) {
    	DBConnection.open();
    	
    	List<HarEntry> entries = har.getLog().getEntries();
        for(int i = 0; i < entries.size(); i++) { // loops through all the entries of the .har file
        	// TODO: GET REQUEST ID AND SAVE IT
			
        	List<HarHeader> headers = entries.get(i).getResponse().getHeaders(); // list of response headers

			for(HarHeader h : headers) {
				String name = h.getName();
				String value = h.getValue();
				
				ResponseHeader responseHeader = new ResponseHeader(); // creates an instance of the ResponseHeader model
				responseHeader.set("name", name);
				responseHeader.set("value", value);
				responseHeader.saveIt(); // saves it to the database
			}
			
		}
        DBConnection.close(); // closes the connection to the database
    }
}