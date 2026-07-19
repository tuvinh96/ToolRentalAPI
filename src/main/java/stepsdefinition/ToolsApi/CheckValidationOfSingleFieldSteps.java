package stepsdefinition.ToolsApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;

import common.Context;
import common.JsonUtils;
import common.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CheckValidationOfSingleFieldSteps {
	private final ScenarioContext scenarioContext;
	String newUrl;
	
	public CheckValidationOfSingleFieldSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	@Given("I have data as {string} and {string} and {string} and {string} and {string} and {string}")
	public void i_have_data_as_and_expected_status_code_and_error_message(String category, String catValue, String result, String resultValue, String avai, String avaiValue) {
		String url = scenarioContext.getContext(Context.URL).toString();
		if(catValue.equalsIgnoreCase("missing")) {
            newUrl = url + "?" + result + "=" + resultValue + "&" + avai + "=" + avaiValue;
		}
		else if(resultValue.equalsIgnoreCase("missing")) {
            newUrl = url + "?" + category + "=" + catValue + "&" + avai + "=" + avaiValue;
        }
        else if(avaiValue.equalsIgnoreCase("missing")) {
            newUrl = url + "?" + category + "=" + catValue + "&" + result + "=" + resultValue;
        }
        else {
            newUrl = url + "?" + category + "=" + catValue + "&" + result + "=" + resultValue + "&" + avai + "=" + avaiValue;
        }
		scenarioContext.setContext(Context.URL, newUrl);
	}
	

	@Then("Api responds Error message {string}")
	public void api_responds_(String expectedMessage) {
		@SuppressWarnings("unchecked")
		HttpResponse<String> response = (HttpResponse<String>) scenarioContext.getContext(Context.RESPONSE_BODY);
		JsonUtils jsonUtils = new JsonUtils();
		if (response.statusCode() != 200) {
			String actualMessage = jsonUtils.getDataByKey(response.body(), "error").get(0);
			assertEquals(expectedMessage, actualMessage);
		}
	}
	
}
