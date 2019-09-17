package br.com.skytef.rest_robot.entities;

import java.io.Serializable;
import java.util.Objects;

public class RequestParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String value;
	private String resource;
	
	public RequestParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestParameter other = (RequestParameter) obj;
		return Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}

	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
