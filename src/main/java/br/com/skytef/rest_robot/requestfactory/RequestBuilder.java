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
				recurso.setValue(retrieveRandomName());
				//System.out.println("Nome gerado: "+ recurso.getValue());
			}else if(recurso.getValue().contains("@random_number_L_")) {
				recurso.setValue(retriveRandomNumber(recurso.getValue()).toString());
			//	System.out.println("Valor gerado: "+ recurso.getValue());
			}else if(recurso.getValue().contains("@random_serial_M_")) {
				recurso.setValue(retrieveRandomSerial(recurso.getValue()).toString());
				System.out.println("Valor gerado: "+ recurso.getValue());
			}
			
		}
		
		request.getResources().forEach(n -> System.out.println("Recurso: "+n.getName() + " Valor: "+n.getValue()));
		
	}
	
	
	//@random_serial
	private String retrieveRandomSerial(String value) {
		value = value.substring(17);
		
		StringBuilder newValue = new StringBuilder(value);
		
		for (int i = 0; i < value.length(); i++) {
			if(value.substring(i,i+1).equals("A")) {
//				NewValue.replaceFirst("A", retrieveRandomNumber().toString());
				newValue.setCharAt(i,  retrieveRandomLetter().charAt(0));
			}else if(value.substring(i,i+1).equals("9")) {
//				value.substring(i).re("9", retrieveRandomNumber().toString());
				newValue.setCharAt(i,  retrieveRandomNumber().toString().charAt(0));
			}
		}
		
		return newValue.toString();
		
	}
	
	private Integer retrieveRandomNumber() {
		return new Random().nextInt(9);
	}
	
	private String retrieveRandomLetter() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char letter = abc.charAt(new Random().nextInt(abc.length()));
		return String.valueOf(letter);
	}
	
	
	private Object retriveRandomNumber(String value) {
		String n = value.split("_")[3];
//		System.out.println(n);
		if(n.length() < 0 || n == null) {
			return new Random().nextInt(10);
		}else if(n.length()<= 9) {
			return new Random().nextInt(Integer.parseInt(n));
			
		}else if(n.length() > 9  && n.length() <= 18) {
			return new Random().nextLong();
		}
		
		return "0";
	}
	
	
	
	
	private String retrieveRandomName() throws IOException {
		int num = new Random().nextInt(6000);
		//System.out.println(num);
		String line = Files.readAllLines(Paths.get("assets/data/names_brazil.csv"),StandardCharsets.UTF_8).get(num);
		//System.out.println(line);
		String name = line.split(",")[0];
		return name;
	}


}
