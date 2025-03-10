package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageSize{

	@JsonProperty("width")
	private Integer width;

	@JsonProperty("height")
	private Integer height;

	public void setWidth(Integer width){
		this.width = width;
	}

	public Integer getWidth(){
		return width;
	}

	public void setHeight(Integer height){
		this.height = height;
	}

	public Integer getHeight(){
		return height;
	}
}