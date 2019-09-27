package br.com.skytef.rest_robot.restsender;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import br.com.skytef.rest_robot.entities.GenericRequest;

public class DoRequest {

	public final HttpResponse DoRequest(GenericRequest gr) {
		if (gr.getMethod().equals("GET")) {
			return doGET(gr);
		} else if (gr.getMethod().equals("POST")) {
			// do post
			if (gr.getPattern().equals("query-string")) {
				return doQueryStringPOST(gr);
			} else if (gr.getPattern().equals("query-json")) {
				return doPOST(gr);
			} else {
				// return invalid
				return null;
			}
		} else {
			// return invalid
			return null;
		}
	}

	private HttpResponse doQueryStringPOST(GenericRequest gr) {

		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
			HttpPost request = new HttpPost(gr.getUri() + gr.queryString());
			HttpResponse response = httpClient.execute(request);
			return response;
		} catch (Exception ex) {
			System.out.println("Erro Post");
			return null;

		}
	}

	private HttpResponse doGET(GenericRequest gr) {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
			HttpGet request = new HttpGet(gr.getUri() + gr.queryString());
			request.addHeader("content-type", "application/json");
			HttpResponse response = httpClient.execute(request);
			return response;
		} catch (Exception ex) {
			System.out.println("Erro Post");
			return null;

		}
	}

	private HttpResponse doPOST(GenericRequest gr) {

		try {
			HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
			HttpPost request = new HttpPost(gr.getUri());
			StringEntity params = new StringEntity(gr.queryPath());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			return response;
		} catch (Exception ex) {
			System.out.println("Erro Post");
			return null;
		}
	}

	public String responseToString(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String responseString = "";
		try {
			responseString = EntityUtils.toString(entity, "UTF-8");
		} catch (ParseException e) {
			return "{error:" + e.getMessage() + "}";
		} catch (IOException e) {
			return "{error:" + e.getMessage() + "}";
		}
		return responseString;
	}
}
