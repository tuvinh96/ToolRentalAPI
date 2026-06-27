package common;

import java.net.http.HttpResponse;
import java.util.Map;

public class RequestUtils {
	public HttpResponse<String> sendRequest(String url, String method, Map<String, String> headers, String body) {
		Request request = new Request();
		if (method.equalsIgnoreCase(HttpMethod.GET.toString())) {
			request.sendGetRequest(url, headers);
		}
		else if (method.equalsIgnoreCase(HttpMethod.POST.toString())) {
			request.sentPostRequest(url, body, headers);
		}
		
		return request.response;
	}
}
