package ovh.garcon.ws;

import static spark.Spark.*;

import java.util.Date;

import ovh.garcon.ws.services.GitHubService;
import ovh.garcon.ws.services.HealthCheckService;
import ovh.garcon.ws.services.RandomService;
import ovh.garcon.ws.utils.JsonTransformer;

/**
 * 
 * Contains the main API of the web service
 * @author begarco
 *
 */
public class GeneralWS {
	public static void main(String[] args) {
		port(11393);
		before(((request, response) -> {
        	response.header("Content-Type", "application/json; charset=utf-8");
        	response.header("Server", "BG Prod Server");
            response.header("Content-Encoding", "gzip");
            Date d = new Date();
        	response.header("Date", d.toString());
			System.out.println("[" + d.toString() + "]" + request.protocol() + " " + request.requestMethod() + " " + request.url() + " from " + request.ip());
			if(request.body()!=null && request.body()!="")
				System.out.println(request.body());
		}));
		after(((request, response) -> {
			
		}));
        get("/", (request, response) -> {
        	response.header("Content-Type", "text/plain; charset=utf-8");
        	return "Welcome to Benoît Garçon's web service ;)";
        });
        get("/health", (request, response) -> {
        	return new HealthCheckService().run(request, response);
        }, new JsonTransformer());
        get("/random", (request, response) -> {
        	return new RandomService().run(request, response);
        }, new JsonTransformer());
        path("/github", () -> {
        	get("", (request, response) -> {
            	return new GitHubService().run(request, response);
            }, new JsonTransformer());
        	get("/*", (request, response) -> {
            	return new GitHubService().run(request, response);
            }, new JsonTransformer());
        	post("/*", (request, response) -> {
            	return new GitHubService().run(request, response);
            }, new JsonTransformer());
        });
    }
}
