package common;

import java.net.http.HttpResponse;
import java.util.Map;

public class RequestUtils {
	Config config;
	public HttpResponse<String> sendRequest(String url, String method, Map<String, String> headers, String body) {
		String testData = System.getProperty("user.dir")+"/src/main/resources/ProjectConfig.properties";
		config = new Config(testData);
		String fullURL = config.getValueByKey("BaseURL") + url;
		System.out.println("Full URL: " + fullURL);
		Request request = new Request();
		if (method.equalsIgnoreCase(HttpMethod.GET.toString())) {
			request.sendGetRequest(fullURL, headers);
		}
		else if (method.equalsIgnoreCase(HttpMethod.POST.toString())) {
			request.sentPostRequest(fullURL, body, headers);
		}
		
		return request.response;
	}
}
