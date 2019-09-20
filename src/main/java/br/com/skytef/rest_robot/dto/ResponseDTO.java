package br.com.skytef.rest_robot.dto;

import java.util.Objects;

public class ResponseDTO {
	private Integer index;
	private String response;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(index, response);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseDTO other = (ResponseDTO) obj;
		return Objects.equals(index, other.index) && Objects.equals(response, other.response);
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
