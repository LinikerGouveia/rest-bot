package br.com.skytef.rest_robot.restsender;




import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.requestfactory.RequestBuilder;

@RestController
public class Default {
	
	

	private RequestBuilder reqbuilder;
	
	@RequestMapping("/post")
	public String ativar(@RequestBody GenericRequest requestValue) {
		requestValue.getParameters().forEach(v -> System.out.println(v.getName()));
		System.out.println(requestValue.toString());
		
		// if content-type  =  application/json
		
		// if queryparam
		
		// if path
		
		
		
		
		try {
			for (int i = 0; i < requestValue.getBulk(); i++) {
				reqbuilder =  new RequestBuilder(requestValue);
				System.out.println("Montagem completa: "+reqbuilder.getBuiltRequest().buildUri());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
		 final String uri = "http://192.168.10.137:7070/consulta";
		    RestTemplate restTemplate = new RestTemplate();
		    String result = restTemplate.getForObject(uri, String.class);
	
//	        System.out.println(result);
	        return result;
	        

	}
	
	
	
	


}
