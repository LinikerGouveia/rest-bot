package br.com.skytef.rest_robot.requestfactory;


import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.entities.RequestParameter;

public class RequestBuilder implements Serializable {

	private static final long serialVersionUID = -7740433445198920388L;
	private GenericRequest request;
	
	public RequestBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestBuilder(GenericRequest request) throws IOException {
		for (RequestParameter parameter : request.getParameters()) {
			if(parameter.getValue().equalsIgnoreCase("@random_name")){
				parameter.setValue(retrieveRandomName());
			}else if(parameter.getValue().contains("@random_number_L_")) {
				parameter.setValue(retriveRandomNumber(parameter.getValue()).toString());
			}else if(parameter.getValue().contains("@random_serial_M_")) {
				parameter.setValue(retrieveRandomSerial(parameter.getValue()).toString());
				System.out.println("Valor gerado: "+ parameter.getValue());
			}
			
		}
		
		request.getParameters().forEach(n -> System.out.println("Parametro: "+n.getName() + " Valor: "+n.getValue()));
		this.request = request;
	}
	
	
	//@random_serial
	private String retrieveRandomSerial(String value) {
		value = value.substring(17);
		
		StringBuilder newValue = new StringBuilder(value);
		
		for (int i = 0; i < value.length(); i++) {
			if(value.substring(i,i+1).equals("A")) {
				newValue.setCharAt(i,  retrieveRandomLetter().charAt(0));
			}else if(value.substring(i,i+1).equals("9")) {
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
		String line = Files.readAllLines(Paths.get("assets/data/names_brazil.csv"),StandardCharsets.UTF_8).get(num);
		String name = line.split(",")[0];
		return name;
	}
	
	public GenericRequest getBuiltRequest() {
		return this.request;
	}


}
