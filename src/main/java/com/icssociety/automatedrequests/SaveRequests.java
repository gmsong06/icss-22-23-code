package com.icssociety.automatedrequests;

import java.util.*;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarHeader;
import de.sstoehr.harreader.model.HarRequest;
import de.sstoehr.harreader.model.HarResponse;

public class SaveRequests {
    public static void save(Har har) {
    	List<HarEntry> entries = har.getLog().getEntries();
    	for(int i = 0; i < entries.size(); i++) { // loops through all the entries of the .har file
			HarEntry entry = entries.get(i);
			
			Request request = new Request(entry, "Ann");
			
			request.saveIt();
		}
    }
    
    public static void SaveRequestHeaders(int requestId, HarRequest request) {
    	
    }
}