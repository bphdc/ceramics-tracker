package auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * key class
 */
public class Keys{

	@JsonProperty("keys")
	private List<KeysItem> keys;

	/**
	 * gets list of keys
	 * @return keys
	 */
	public List<KeysItem> getKeys(){
		return keys;
	}
}