package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputItem{

	@JsonProperty("url")
	private String url;

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}