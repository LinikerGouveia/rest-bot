package br.com.skytef.rest_robot.restsender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

		requestValue.getParameters().forEach(v -> System.out.println(v.getName()));
		System.out.println(requestValue.toString());
		List<ResponseDTO> responseList = new ArrayList<ResponseDTO>();

		try {
			for (int i = 0; i < requestValue.getBulk(); i++) {

				ResponseDTO newResponse = new ResponseDTO();
				reqbuilder = new RequestBuilder(requestValue);
				System.out.println("Montagem completa: " + reqbuilder.getBuiltRequest().getUri()
						+ reqbuilder.getBuiltRequest().queryString());
				System.out.println(reqbuilder.getBuiltRequest().toString());
				HttpResponse response = DoRequest(reqbuilder.getBuiltRequest());

				String responseString = responseToString(response);

				newResponse.setIndex(i);
				newResponse.setResponse(responseString);
				responseList.add(newResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseList;
	}

}
