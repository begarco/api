package ovh.garcon.ws.services;

import java.util.HashMap;

import org.apache.commons.math3.random.MersenneTwister;

import spark.Request;
import spark.Response;

/**
 * 
 * Generates random numbers
 * @author begarco
 *
 */
public class RandomService implements IService {
	
	private static MersenneTwister generator = new MersenneTwister(11031993);
	private HashMap<String, Object> data = new HashMap<>();

	@Override
	public Object run(Request request, Response response) {
		data.put("generator", generator.getClass().getSimpleName());
		HashMap<String, Object> values = new HashMap<>();
		values.put("double", generator.nextDouble());
		values.put("float", generator.nextFloat());
		values.put("long", generator.nextLong());
		values.put("int", generator.nextInt());
		data.put("values", values);
		return data;
	}

}
