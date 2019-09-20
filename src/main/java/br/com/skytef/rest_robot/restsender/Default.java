package br.com.skytef.rest_robot.restsender;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.skytef.rest_robot.dto.ResponseDTO;
import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.requestfactory.RequestBuilder;

@RestController
public class Default {
	
	

	private RequestBuilder reqbuilder;
	
	@RequestMapping("/post")
	public List<ResponseDTO> ativar(@RequestBody GenericRequest requestValue) {
		requestValue.getParameters().forEach(v -> System.out.println(v.getName()));
		System.out.println(requestValue.toString());
		
		// if content-type  =  application/json
		
		// if queryparam
		
		// if path
		
		
		
		//List<String> responseList = new ArrayList<String>();
		List<ResponseDTO> responseList = new ArrayList<ResponseDTO>();
		try {
			for (int i = 0; i < requestValue.getBulk(); i++) {
				
				ResponseDTO newResponse =  new ResponseDTO();
				reqbuilder =  new RequestBuilder(requestValue);
				System.out.println("Montagem completa: "+reqbuilder.getBuiltRequest().getUri()+reqbuilder.getBuiltRequest().queryString());
		
				HttpResponse response =  doQueryStringPOST(reqbuilder.getBuiltRequest());
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity, "UTF-8");
				newResponse.setIndex(i);
				newResponse.setResponse(responseString);
				//System.out.println("resposta: "+ responseString);
				
				responseList.add(newResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return responseList;
//		 final String uri = "http://192.168.10.137:7070/consulta";
//		    RestTemplate restTemplate = new RestTemplate();
//		    String result = restTemplate.getForObject(uri, String.class);
//	
//	        return result;
	        

	}
	
	
	private HttpResponse doPOST(GenericRequest gr) {
	
		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		    HttpPost request = new HttpPost(gr.getUri());
		    StringEntity params =new StringEntity(gr.queryPath());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		
		 return response;
		
		}catch (Exception ex) {
			System.out.println("Erro Post");
		    return null;
		
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}


	}
	
	private HttpResponse doQueryStringPOST(GenericRequest gr) {
		
		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		    HttpPost request = new HttpPost(gr.getUri()+gr.queryString());
		   
	
		    HttpResponse response = httpClient.execute(request);
		
		 return response;
		
		}catch (Exception ex) {
			System.out.println("Erro Post");
		    return null;
		
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}


	}
	
	
	private HttpResponse doGET(GenericRequest gr) {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		    HttpGet request = new HttpGet(gr.getUri()+gr.queryString());
		    request.addHeader("content-type", "application/json");
		    HttpResponse response = httpClient.execute(request);
		
		 return response;
		
		}catch (Exception ex) {
			System.out.println("Erro Post");
		    return null;
		
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
	}
	
	

}
