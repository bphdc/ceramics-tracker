package com.hiveai;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public void setOutput(List<OutputItem> output){
		this.output = output;
	}

	public List<OutputItem> getOutput(){
		return output;
	}

	public void setInput(Input input){
		this.input = input;
	}

	public Input getInput(){
		return input;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}
}