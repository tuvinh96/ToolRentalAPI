package common;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Request {
	
	HttpResponse<String> response;
	public Request() {
		
	}
	
	public HttpResponse<String> sendGetRequest(String url, Map<String, String> headers) {
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url)).GET();
		headers.forEach(requestBuilder::header);
		HttpRequest request = requestBuilder.build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.response;
	}
	
	public HttpResponse<String> sentPostRequest(String url, String requestBody, Map<String, String> headers) {
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest.Builder reqBuilder = HttpRequest.newBuilder().uri(URI.create(url)).POST(BodyPublishers.ofString(requestBody));
		headers.forEach(reqBuilder::header);
		HttpRequest request = reqBuilder.build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.response;
	}
	
}
