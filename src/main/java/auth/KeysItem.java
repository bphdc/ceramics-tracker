package auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * key item class
 */
public class KeysItem{

	@JsonProperty("kty")
	private String kty;

	@JsonProperty("e")
	private String E;

	@JsonProperty("use")
	private String use;

	@JsonProperty("kid")
	private String kid;

	@JsonProperty("alg")
	private String alg;

	@JsonProperty("n")
	private String N;

	/**
	 * gets kty
	 * @return kty
	 */
	public String getKty(){
		return kty;
	}

	/**
	 * gets e
	 * @return E
	 */
	public String getE(){
		return E;
	}

	/**
	 * gets use
	 * @return use
	 */
	public String getUse(){
		return use;
	}

	/**
	 * gets kid
	 * @return kid
	 */
	public String getKid(){
		return kid;
	}

	/**
	 * gets alg
	 * @return alg
	 */
	public String getAlg(){
		return alg;
	}

	/**
	 * gets n
	 * @return n
	 */
	public String getN(){
		return N;
	}
}