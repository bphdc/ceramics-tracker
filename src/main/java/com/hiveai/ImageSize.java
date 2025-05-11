package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Image size class for hive.ai
 */
public class ImageSize{

	@JsonProperty("width")
	private Integer width;

	@JsonProperty("height")
	private Integer height;

	/**
	 * set image width
	 * @param width
	 */
	public void setWidth(Integer width){
		this.width = width;
	}

	/**
	 * get image width
	 * @return width
	 */
	public Integer getWidth(){
		return width;
	}

	/**
	 * Sets image height
	 * @param height
	 */
	public void setHeight(Integer height){
		this.height = height;
	}

	/**
	 * gets image height
	 * @return
	 */
	public Integer getHeight(){
		return height;
	}
}