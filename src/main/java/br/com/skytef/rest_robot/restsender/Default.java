package br.com.skytef.rest_robot.restsender;




import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.requestfactory.RequestBuilder;

@RestController
public class Default {
	
	

	private RequestBuilder reqbuilder;
	
	@RequestMapping("/post")
	public String ativar(@RequestBody GenericRequest requestValue) {
		
		requestValue.getResources().forEach(v -> System.out.println(v.getName()));
		
		try {
			reqbuilder =  new RequestBuilder(requestValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		 final String uri = "http://192.168.10.137:7070/consulta";
		    RestTemplate restTemplate = new RestTemplate();
		    String result = restTemplate.getForObject(uri, String.class);
//	        System.out.println(result);
	        return result;
	        

	}
	
	
	
	


}
