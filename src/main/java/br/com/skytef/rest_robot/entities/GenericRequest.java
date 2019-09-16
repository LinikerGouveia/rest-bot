package br.com.skytef.rest_robot.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GenericRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	private String ip;
	private Integer port;
	private List<RequestResource> resources;
	
	
	public GenericRequest() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(ip, port, resources);
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
				&& Objects.equals(resources, other.resources);
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
	public List<RequestResource> getResources() {
		return resources;
	}
	public void setResources(List<RequestResource> resources) {
		this.resources = resources;
	}
	
	

}
