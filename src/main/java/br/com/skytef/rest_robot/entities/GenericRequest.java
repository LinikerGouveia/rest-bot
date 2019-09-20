package br.com.skytef.rest_robot.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.omg.CORBA.Request;
import org.springframework.web.bind.annotation.RequestBody;
public class GenericRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ip;
	private Integer port;
	private String resource;
	private String method;
	private Integer bulk ;
	private String pattern;
	
	private List<RequestParameter> parameters;
	

	
	
	@Override
	public int hashCode() {
		return Objects.hash(bulk, ip, method, parameters, pattern, port, resource);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericRequest other = (GenericRequest) obj;
		return Objects.equals(bulk, other.bulk) && Objects.equals(ip, other.ip) && Objects.equals(method, other.method)
				&& Objects.equals(parameters, other.parameters) && Objects.equals(pattern, other.pattern)
				&& Objects.equals(port, other.port) && Objects.equals(resource, other.resource);
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getBulk() {
		return bulk;
	}
	public void setBulk(Integer bulk) {
		this.bulk = bulk;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public List<RequestParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<RequestParameter> parameters) {
		this.parameters = parameters;
	}
	
	public String getUri() {
		String uri = "http://";
		uri = uri.concat(this.ip);
		uri = uri.concat(":"+this.port);
		uri = uri.concat(this.resource);
		return uri;
	}
	
	public String queryString() {
		String uri = "";
//		uri = uri.concat(this.ip);
//		uri = uri.concat(":"+this.port);
//		uri = uri.concat(this.resource);
		if(parameters.size() > 0 ) {
			uri = uri.concat("?");
			for (int i = 0; i < parameters.size(); i++) {
				uri = uri.concat(parameters.get(i).getName()+"="+parameters.get(i).getValue());
				if(i != parameters.size() - 1) {
					uri = uri.concat("&");
				}
			}
		}
		return uri;
	}
	
	public String queryPath() {

			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
			StringBuilder paramJson =  new StringBuilder();
			paramJson.append("{");
			for (RequestParameter param : this.parameters) {
				paramJson.append("\""+param.getName()+"\"");
				paramJson.append(":");
				paramJson.append("\""+param.getValue()+"\"");
				paramJson.append("\n");
				if(!param.equals(parameters.get(parameters.size()-1))) {
					paramJson.append(",");
				}else {
					paramJson.append("}");
				}
			}
			System.out.println("Json gerado: "+paramJson.toString());
			
		return paramJson.toString();
	}

	@Override
	public String toString() {
		return "GenericRequest [ip=" + ip + ", port=" + port + " method=" + method
				+ ", bulk=" + bulk + " pattern=" + pattern + ", parameters=" + parameters + "]";
	}

	public GenericRequest clone() {
		GenericRequest cloned = new GenericRequest();
		cloned.ip = this.ip ;
		cloned.port = this.port;
		cloned.resource =  this.resource;
		cloned.parameters = this.parameters;
		cloned.bulk =  this.bulk;
		cloned.method =  this.method;
		cloned.pattern =  this.pattern;
		return cloned;
	}
}
