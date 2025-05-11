package com.hiveai;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class representing hive.ai class
 */
public class Response{

	@JsonProperty("output")
	private List<OutputItem> output;

	@JsonProperty("input")
	private Input input;

	@JsonProperty("model")
	private String model;

	@JsonProperty("id")
	private String id;

	@JsonProperty("version")
	private String version;

	/**
	 * Set the output
	 * @param output
	 */
	public void setOutput(List<OutputItem> output){
		this.output = output;
	}

	/**
	 * get the output
	 * @return output list
	 */
	public List<OutputItem> getOutput(){
		return output;
	}

	/**
	 * set input
	 * @param input
	 */
	public void setInput(Input input){
		this.input = input;
	}

	/**
	 * get input
	 * @return input
	 */
	public Input getInput(){
		return input;
	}

	/**
	 * set model
	 * @param model
	 */
	public void setModel(String model){
		this.model = model;
	}

	/**
	 * get model
	 * @return model
	 */
	public String getModel(){
		return model;
	}

	/**
	 * Set id
	 * @param id
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * get id
	 * @return
	 */
	public String getId(){
		return id;
	}

	/**
	 * set version
	 * @param version
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * get version
	 * @return
	 */
	public String getVersion(){
		return version;
	}
}