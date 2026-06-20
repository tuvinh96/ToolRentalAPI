package stepsdefinition.ToolsApi;

import static org.testng.AssertJUnit.assertEquals;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	String url;
	HttpResponse<String> response;

	@When("send request with valid URL and method and params")
	public void send_request_with_valid_url_and_method_and_params() {
		url = "https://simple-tool-rental-api.glitch.me?category=ladders&results=2&available=1";
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Accept-Encoding", "gzip,deflate,br")
				.header("Accept", "application/json").GET().build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("Api responds status code {int} and list of tools")
	public void api_responds_status_code_and_list_of_tools(int expectedStatusCode) {
		assertEquals(expectedStatusCode, response.statusCode());
	}

}