package ovh.garcon.ws.services;

import spark.Request;
import spark.Response;

/**
 * 
 * Interface of services
 * @author begarco
 *
 */
public interface IService {

	Object run(Request request, Response response);

}
