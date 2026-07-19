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
	private ScenarioContext scenarioContext;
	
	public CheckRegisterSuccessfullySteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("I have request body")
	public void i_have_request_body(DataTable requestBodyTable) {
		String requestBodyName = requestBodyTable.asMaps().get(0).get("requestBody");
		JsonUtils jsonUtils = new JsonUtils();
		String requestBody = jsonUtils.readJsonFile(requestBodyName);
		scenarioContext.setContext(Context.REQUEST_BODY, requestBody);
	}
	
	@When("send post request")
	public void send_request_with_valid_url_and_method_and_params() {
		RequestUtils req = new RequestUtils();
		String url = (String) scenarioContext.getContext(Context.URL);
		String method = (String) scenarioContext.getContext(Context.METHOD);
		String requestBody = (String) scenarioContext.getContext(Context.REQUEST_BODY);
		@SuppressWarnings("unchecked")
		Map<String, String> headers = (Map<String, String>) scenarioContext.getContext(Context.HEADERS);
		HttpResponse<String> response = req.sendRequest(url, method, headers, requestBody);
		scenarioContext.setContext(Context.RESPONSE_BODY, response);
	}
	

	
	@SuppressWarnings("unchecked")
	@Then("Api responds status code with {string}")
	public void api_responds_status_code(String expectedStatusCode) {
		HttpResponse<String> response = (HttpResponse<String>) scenarioContext.getContext(Context.RESPONSE_BODY);
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
	}

	@SuppressWarnings("unchecked")
	@Then("Api responds body")
	public void api_responds_body() {
		HttpResponse<String> response = (HttpResponse<String>) scenarioContext.getContext(Context.RESPONSE_BODY);
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
