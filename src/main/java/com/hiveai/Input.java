package com.hiveai;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input class for hive.ai integration
 */
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

	/**
	 * gets seed
	 * @return seed
	 */
	public Integer getSeed(){
		return seed;
	}

	/**
	 * set output format
	 * @param outputFormat
	 */
	public void setOutputFormat(String outputFormat){
		this.outputFormat = outputFormat;
	}

	/**
	 * get output format
	 * @return output format
	 */
	public String getOutputFormat(){
		return outputFormat;
	}

	/**
	 * Sets image size
	 * @param imageSize
	 */
	public void setImageSize(ImageSize imageSize){
		this.imageSize = imageSize;
	}

	/**
	 * gets image size
	 * @return image size
	 */
	public ImageSize getImageSize(){
		return imageSize;
	}

	/**
	 * sets prompt
	 * @param prompt
	 */
	public void setPrompt(String prompt){
		this.prompt = prompt;
	}

	/**
	 * gets prompt
	 * @return prompt
	 */
	public String getPrompt(){
		return prompt;
	}

	/**
	 * gets output quallity
	 * @param outputQuality
	 */
	public void setOutputQuality(Integer outputQuality){
		this.outputQuality = outputQuality;
	}

	/**
	 * Gets output quality
	 * @return output quality
	 */
	public Integer getOutputQuality(){
		return outputQuality;
	}

	/**
	 * sets num images
	 * @param numImages
	 */
	public void setNumImages(Integer numImages){
		this.numImages = numImages;
	}

	/**
	 * gets num images
	 * @return number of images
	 */
	public Integer getNumImages(){
		return numImages;
	}

	/**
	 * set numinference steps
	 * @param numInferenceSteps
	 */
	public void setNumInferenceSteps(Integer numInferenceSteps){
		this.numInferenceSteps = numInferenceSteps;
	}

	/**
	 * get num inference steps
	 * @return
	 */
	public Integer getNumInferenceSteps(){
		return numInferenceSteps;
	}
}