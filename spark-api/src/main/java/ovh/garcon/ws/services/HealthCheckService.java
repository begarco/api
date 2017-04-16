package ovh.garcon.ws.services;

import java.util.HashMap;

import spark.Request;
import spark.Response;

/**
 * 
 * Provides information about server's state
 * @author begarco
 *
 */
public class HealthCheckService implements IService {
	
	private HashMap<String, Object> data = new HashMap<>();

	@Override
	public Object run(Request request, Response response) {
		data.put("status", "ok");
		return data;
	}
	
}
