package br.com.skytef.rest_robot.restsender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.skytef.rest_robot.dto.ResponseDTO;
import br.com.skytef.rest_robot.entities.GenericRequest;
import br.com.skytef.rest_robot.requestfactory.RequestBuilder;

@RestController
public class Default extends DoRequest {

	private RequestBuilder reqbuilder;

	@RequestMapping("/dorequest")
	public List<ResponseDTO> doReq(@RequestBody GenericRequest requestValue) {
		List<ResponseDTO> responseList = new ArrayList<ResponseDTO>();
		try {
			
			if(requestValue.getBulk() == -1) {
				for (int i = 0; i < 10; i++) {
					ResponseDTO newResponse = new ResponseDTO();
					reqbuilder = new RequestBuilder(requestValue);
					TimeUnit.MILLISECONDS.sleep(requestValue.getDelay());
					HttpResponse response = DoRequest(reqbuilder.getBuiltRequest());
					String responseString = responseToString(response);
					newResponse.setIndex(i);
					newResponse.setResponse(responseString);
					System.out.println("Status:  " + response.getStatusLine().getStatusCode()
							+ "\nResposta: "+responseString);
					responseList.add(newResponse);
					i--;
				}
			}else {
				for (int i = 0; i < requestValue.getBulk(); i++) {
					ResponseDTO newResponse = new ResponseDTO();
					reqbuilder = new RequestBuilder(requestValue);
					TimeUnit.MILLISECONDS.sleep(requestValue.getDelay());
					HttpResponse response = DoRequest(reqbuilder.getBuiltRequest());
					String responseString = responseToString(response);
					newResponse.setIndex(i);
					newResponse.setResponse(responseString);
					responseList.add(newResponse);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}
		return responseList;
	}
}
