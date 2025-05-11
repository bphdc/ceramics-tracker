package auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * token response class
 */
public class TokenResponse {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonProperty("id_token")
	private String idToken;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("expires_in")
	private int expiresIn;

	/**
	 * gets the access token
	 * @return access token
	 */
	public String getAccessToken(){
		return accessToken;
	}

	/**
	 * gets the refresh token
	 * @return token
	 */
	public String getRefreshToken(){
		return refreshToken;
	}

	/**
	 * gets the id token
	 * @return token
	 */
	public String getIdToken(){
		return idToken;
	}

	/**
	 * gets token type
	 * @return type
	 */
	public String getTokenType(){
		return tokenType;
	}

	/**
	 * get expires in
	 * @return expiresIn
	 */
	public int getExpiresIn(){
		return expiresIn;
	}
}