package br.com.skytef.rest_robot.entities;

import java.io.Serializable;
import java.util.Objects;

public class RequestResource implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String value;
	
	public RequestResource(String name, String value) {
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
		RequestResource other = (RequestResource) obj;
		return Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}

	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}

}