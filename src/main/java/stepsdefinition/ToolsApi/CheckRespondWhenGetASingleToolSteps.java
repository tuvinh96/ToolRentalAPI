package stepsdefinition.ToolsApi;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckRespondWhenGetASingleToolSteps {
	String url;
	HttpResponse<String> response;

	@When("Send a tool id")
	public void send_a_tool_id() {
		url = "https://simple-tool-rental-api.glitch.me/tools/5774?user-manual=true";
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Accept-encoding", "gzip,deflate,br")
				.header("Accept", "application/json").GET().build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("Return a tool from inventory")
	public void return_a_tool_from_inventory(int expectedStatusCode) {
		assertEquals(expectedStatusCode, response.statusCode());
	}
}
