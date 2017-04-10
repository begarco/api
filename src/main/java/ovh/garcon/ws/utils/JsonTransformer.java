package ovh.garcon.ws.utils;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * 
 * Generates JSON from an Object
 * @author begarco
 *
 */
public class JsonTransformer implements ResponseTransformer {

	private Gson gson = new Gson();

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}
}