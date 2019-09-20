package br.com.skytef.rest_robot.requestfactory;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.entities.RequestParameter;

public class RequestBuilder implements Serializable {

	private static final long serialVersionUID = -7740433445198920388L;
	private GenericRequest builtRequest;

	public RequestBuilder() {
		// TODO Auto-generated constructor stub
	}

	public RequestBuilder(GenericRequest request) throws IOException {
		builtRequest = new GenericRequest();
		builtRequest.setIp(request.getIp());
		builtRequest.setPort(request.getPort());
		builtRequest.setBulk(request.getBulk());
		builtRequest.setPattern(request.getPattern());
		builtRequest.setMethod(request.getMethod());
		
		builtRequest.setResource(factoryResourcePath(request.getResource()));
		
		
		builtRequest.setParameters(new ArrayList<RequestParameter>());
		for (RequestParameter parameter : request.getParameters()) {
			RequestParameter newParam = new RequestParameter();
			newParam.setName(parameter.getName());
			if (parameter.getValue().equalsIgnoreCase("@random_name")) {
				newParam.setValue(retrieveRandomName());
			} else if (parameter.getValue().contains("@random_number_L_")) {
				newParam.setValue(retriveRandomNumber(parameter.getValue()).toString());
			} else if (parameter.getValue().contains("@random_serial_M_")) {
				newParam.setValue(retrieveRandomSerial(parameter.getValue()).toString());
			} else {
				newParam.setValue(parameter.getValue());
			}

			builtRequest.getParameters().add(newParam);
		}
	}
	
	
	
	private String factoryResourcePath(String resource){
		String[] resourceSplited = resource.split("/");
		String newResource = "";
		for (String rs : resourceSplited) {
			if(rs.contains("@{")) {
				//int indexBegin = request.getResource().indexOf("@{");
				//int indexEnd = request.getResource().indexOf("}");
				//String value = request.getResource().substring(indexBegin,indexEnd+1);
			//	System.out.println("recurso pré fatorado: " + value);
				if (rs.contains("random_serial_M_")) {
							
							// split resource by /
					rs = retrieveRandomSerial(rs.replaceAll("\\{", "").replaceAll("\\}", ""));	
					newResource += rs + "/";		
					//value =  retrieveRandomSerial(value.replaceAll("\\{", "").replaceAll("\\}", ""));
					//request.setResource(value.);
				}
			}else {
				newResource += rs + "/";	
			}
		}
		newResource = newResource.substring(0,newResource.length()-1);
		System.out.println("recurso pós fatorado: " + newResource);
		
		
		
		return newResource;
	}
	

	private String retrieveRandomSerial(String value) {
		value = value.substring(17);

		StringBuilder newValue = new StringBuilder(value);

		for (int i = 0; i < value.length(); i++) {
			if (value.substring(i, i + 1).equals("A")) {
				newValue.setCharAt(i, retrieveRandomLetter().charAt(0));
			} else if (value.substring(i, i + 1).equals("9")) {
				newValue.setCharAt(i, retrieveRandomNumber().toString().charAt(0));
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
		if (n.length() < 0 || n == null) {
			return new Random().nextInt(10);
		} else if (n.length() <= 9) {
			return new Random().nextInt(Integer.parseInt(n));
		} else if (n.length() > 9 && n.length() <= 18) {
			return new Random().nextLong();
		}
		return "0";
	}

	private String retrieveRandomName() throws IOException {
		int num = new Random().nextInt(6000);
		String line = Files.readAllLines(Paths.get("assets/data/names_brazil.csv"), StandardCharsets.UTF_8).get(num);
		String name = line.split(",")[0];
		return name;
	}

	public GenericRequest getBuiltRequest() {
		return this.builtRequest;
	}

}
