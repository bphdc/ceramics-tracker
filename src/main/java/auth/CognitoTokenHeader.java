package auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * the cognito token header class
 */
public class CognitoTokenHeader{

	@JsonProperty("kid")
	private String kid;

	@JsonProperty("alg")
	private String alg;

	/**
	 * gets the kid value
	 * @return kid
	 */
	public String getKid(){
		return kid;
	}

	/**
	 * gets the alg value
	 * @return alg
	 */
	public String getAlg(){
		return alg;
	}
}