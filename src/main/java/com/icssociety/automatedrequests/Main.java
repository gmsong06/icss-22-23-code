package com.icssociety.automatedrequests;

import org.javalite.activejdbc.*;

import java.util.*;
import java.io.*;
 

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarEntry;

import java.util.*;
import java.io.*;

public class Main {
    
	public static void main(String[] args) throws HarReaderException {
		DBConnection.open();
		
		removeAllRequests();
		removeAllRequestHeaders();
		removeAllResponseHeaders();
		
		HarReader harReader = new HarReader();
		Har har = harReader.readFromFile(new File("./data/Extempore.har"));

		SaveRequests.save(har);
		
		System.out.println("STARTED MODIFYING");
		generateModifiedRequests(Request.findById(18), 18, "DELETE_HEADER");
		System.out.println("STOPPED MODIFYING");
		
		DBConnection.close();
		
	}

	public static void generateModifiedRequests(Request request, int id, String strategy) {
		if(strategy.equals("DELETE_HEADER")) {
			GenerationStrategy.deleteHeader(request, id);
		}
	}
	public static void removeAllRequests() {
		Base.exec("DELETE FROM requests");
		Base.exec("ALTER TABLE requests AUTO_INCREMENT = 1");
	}
	
	public static void removeAllRequestHeaders() {
		Base.exec("DELETE FROM request_headers");
		Base.exec("ALTER TABLE request_headers AUTO_INCREMENT = 1");
	}
	
	public static void removeAllResponseHeaders() {
		Base.exec("DELETE FROM response_headers");
		Base.exec("ALTER TABLE response_headers AUTO_INCREMENT = 1");
	}
	
}
