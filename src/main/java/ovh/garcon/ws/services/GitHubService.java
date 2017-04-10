package ovh.garcon.ws.services;

import spark.Request;
import spark.Response;

public class GitHubService implements IService {

	@Override
	public Object run(Request request, Response response) {
		return "GitHub API of Benoît Garçon";
	}

}
