package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Input{

	@JsonProperty("seed")
	private Integer seed;

	@JsonProperty("output_format")
	private String outputFormat;

	@JsonProperty("image_size")
	private ImageSize imageSize;

	@JsonProperty("prompt")
	private String prompt;

	@JsonProperty("output_quality")
	private Integer outputQuality;

	@JsonProperty("num_images")
	private Integer numImages;

	@JsonProperty("num_inference_steps")
	private Integer numInferenceSteps;

	public void setSeed(Integer seed){
		this.seed = seed;
	}

	public Integer getSeed(){
		return seed;
	}

	public void setOutputFormat(String outputFormat){
		this.outputFormat = outputFormat;
	}

	public String getOutputFormat(){
		return outputFormat;
	}

	public void setImageSize(ImageSize imageSize){
		this.imageSize = imageSize;
	}

	public ImageSize getImageSize(){
		return imageSize;
	}

	public void setPrompt(String prompt){
		this.prompt = prompt;
	}

	public String getPrompt(){
		return prompt;
	}

	public void setOutputQuality(Integer outputQuality){
		this.outputQuality = outputQuality;
	}

	public Integer getOutputQuality(){
		return outputQuality;
	}

	public void setNumImages(Integer numImages){
		this.numImages = numImages;
	}

	public Integer getNumImages(){
		return numImages;
	}

	public void setNumInferenceSteps(Integer numInferenceSteps){
		this.numInferenceSteps = numInferenceSteps;
	}

	public Integer getNumInferenceSteps(){
		return numInferenceSteps;
	}
}