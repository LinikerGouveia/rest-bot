package br.com.skytef.rest_robot.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GenericRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	private String ip;
	private Integer port;
	private String resource;

	
	private List<RequestParameter> parameters;
	
	
	public GenericRequest() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(ip, port, parameters);
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
		return Objects.equals(ip, other.ip) && Objects.equals(port, other.port)
				&& Objects.equals(parameters, other.parameters);
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
	public List<RequestParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<RequestParameter> parameters) {
		this.parameters = parameters;
	}
	
	public String buildUri() {
		String uri = "http://";
		uri = uri.concat(this.ip);
		uri = uri.concat(":"+this.port);
		uri = uri.concat(this.resource);
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
}
