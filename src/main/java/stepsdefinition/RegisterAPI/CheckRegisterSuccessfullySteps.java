package stepsdefinition.RegisterAPI;

import static org.testng.AssertJUnit.assertEquals;

import java.net.http.HttpResponse;
import java.util.Map;

import common.RequestUtils;
import common.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckRegisterSuccessfullySteps {
	String requestBody;
	HttpResponse<String> response;
	private ScenarioContext scenarioContext;
	
	public CheckRegisterSuccessfullySteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("I have request body")
	public void i_have_request_body() {
		requestBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
	}
	
//	@When("send request")
//	public void send_request_with_valid_url_and_method_and_params() {
//		RequestUtils req = new RequestUtils();
//		String url = (String) scenarioContext.getContextByKey("url");
//		String method = (String) scenarioContext.getContextByKey("method");
//		Map<String, String> headers = (Map<String, String>) scenarioContext.getContextByKey("headers");
//		response = req.sendRequest(url, method, headers, requestBody);
//		
//	}
	
	@Then("Api responds status code {string}")
	public void api_responds_status_code(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
	}

	@Then("Api responds body")
	public void api_responds_body() {
	}

}
