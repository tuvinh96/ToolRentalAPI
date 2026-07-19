package stepsdefinition.RegisterAPI;

import java.util.HashMap;
import java.util.Map;

import common.Context;
import common.JsonUtils;
import common.ScenarioContext;
import io.cucumber.java.en.Given;

public class CheckRegisterValidation {
	ScenarioContext scenarioContext;
	public CheckRegisterValidation(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("I have data as {string} and {string} and {string} and {string}")
	public void i_have_data_as_and_and_and(String username, String usernameValue, String password, String passwordValue) {
		String requestBody = scenarioContext.getContext(Context.REQUEST_BODY).toString();
		Map<String,String> fields = new HashMap<String, String>();
		fields.put(username, usernameValue);
		fields.put(password, passwordValue);
		JsonUtils jsonUtils = new JsonUtils();
		String newRequestBody = jsonUtils.changeJsonBodyByValue(requestBody, fields);
		scenarioContext.setContext(Context.REQUEST_BODY, newRequestBody);
		
	}
}
