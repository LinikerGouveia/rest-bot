package br.com.skytef.rest_robot.requestfactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.entities.RequestResource;

public class RequestBuilder implements Serializable {

	private static final long serialVersionUID = -7740433445198920388L;

	public RequestBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestBuilder(GenericRequest request) throws IOException {
		for (RequestResource recurso : request.getResources()) {
			if(recurso.getValue().equalsIgnoreCase("@random_name")){
				System.out.println(retrieveRandomName());
			}
		}
	}
	
	
	private String retrieveRandomName() throws IOException {
		int num = new Random().nextInt(6000);
		//System.out.println(num);
		String line = Files.readAllLines(Paths.get("assets/data/names_brazil.csv"),StandardCharsets.UTF_8).get(num);
		//System.out.println(line);
		String name = line.split(",")[0];
		return name;
	}

	
	
    public  void readLineFromFile() {
    	
        try {
        BufferedReader br = new BufferedReader(new FileReader("assets/data/names_brazil.csv"));
        System.out.println(br.lines().count());
       String thisLine = br.readLine(); 
        System.out.println(thisLine);
        } // end try
       
      catch (IOException e) {
        System.err.println("Error: " + e);
      }
   } 
	

}
