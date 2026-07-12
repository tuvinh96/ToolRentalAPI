package stepsdefinition.RegisterAPI;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;

import common.Context;
import common.JsonUtils;
import common.RequestUtils;
import common.ScenarioContext;
import io.cucumber.datatable.DataTable;
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
	public void i_have_request_body(DataTable requestBodyTable) {
		String requestBodyName = requestBodyTable.asMaps().get(0).get("requestBody");
		JsonUtils jsonUtils = new JsonUtils();
		requestBody = jsonUtils.readJsonFile(requestBodyName);
		
	}
	
	@When("send post request")
	public void send_request_with_valid_url_and_method_and_params() {
		RequestUtils req = new RequestUtils();
		String url = (String) scenarioContext.getContext(Context.URL);
		String method = (String) scenarioContext.getContext(Context.METHOD);
		Map<String, String> headers = (Map<String, String>) scenarioContext.getContext(Context.HEADERS);
		response = req.sendRequest(url, method, headers, requestBody);
	}
	

	
	@Then("Api responds status code {string}")
	public void api_responds_status_code(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
	}

	@Then("Api responds body")
	public void api_responds_body() {
		JsonUtils jsonUtils = new JsonUtils();
		ArrayList<String> ids = jsonUtils.getDataByKey(response.body(), "id");
		ArrayList<String> tokens = jsonUtils.getDataByKey(response.body(), "token");
		for (String id : ids) {
			boolean checked = !id.isEmpty() || !id.isBlank();
			assertTrue(checked);
		}
		for (String token : tokens) {
			boolean checked = !token.isEmpty() || !token.isBlank();
			assertTrue(checked);
		}
	}

}
