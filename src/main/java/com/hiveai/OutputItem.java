package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class representing output item for hive.ai integration
 */
public class OutputItem{

	@JsonProperty("url")
	private String url;

	/**
	 * set url
	 * @param url
	 */
	public void setUrl(String url){
		this.url = url;
	}

	/**
	 * get url
	 * @return url
	 */
	public String getUrl(){
		return url;
	}
}