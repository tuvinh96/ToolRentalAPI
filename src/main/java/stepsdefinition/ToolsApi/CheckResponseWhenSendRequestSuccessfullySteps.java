package stepsdefinition.ToolsApi;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.Request;
import common.RequestUtils;
import common.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	String url;
	String method;
	String newUrl;
	String category;
	int result;
	String available;
	HttpResponse<String> response;
	Map<String, String> headers = new HashMap<String, String>();
	private ScenarioContext scenarioContext;
	
	public CheckResponseWhenSendRequestSuccessfullySteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
		
	}

	@Given("I have header")
	public void i_have_header(DataTable headerTable) {
		List<Map<String, String>> originalHeaders = headerTable.asMaps(String.class, String.class);
		for (Map<String, String> header : originalHeaders) {
			String key = header.get("key");
			String value = header.get("value");
			headers.put(key,value);
		}
		scenarioContext.setContext("headers", headers);
	}

	@Given("I have url and method")
	public void i_have_url_and_method(DataTable urlAndMethodTable) {
		List<Map<String, String>> originalUrlMethods = urlAndMethodTable.asMaps(String.class, String.class);
		url = originalUrlMethods.get(0).get("url");
		scenarioContext.setContext("url",url);
		method = originalUrlMethods.get(0).get("method");
		scenarioContext.setContext("method", method);
	}
	
	@Given("I have {string} and {string} of tools and {string} status")
	public void i_have_and_of_tools_and_status(String givenCategory, String givenResult, String givenAvailable) {
		category = givenCategory;
		result = Integer.parseInt(givenResult);
		available = givenAvailable;
		newUrl = url.replace("@category", givenCategory).replace("@results", givenResult).replace("@available", givenAvailable);
	}

	@When("send request")
	public void send_request_with_valid_url_and_method_and_params() {
		RequestUtils req = new RequestUtils();
		response = req.sendRequest(newUrl, method, headers, "");
		
	}

	@Then("Api responds status code {string} and list of tools")
	public void api_responds_status_code_and_list_of_tools(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
		int actualToolNumber = 0;
		int actualValidIDNumber = 0;
		int actualValidCategoryNumber = 0;
		int actualStockNumber = 0;
		String responseJson = response.body();
		JSONParser parser = new JSONParser();
		try {
			Object responseObj = parser.parse(responseJson);
			if (responseObj instanceof JSONArray) {
				JSONArray resArray = (JSONArray) responseObj;
				actualToolNumber = resArray.size();
				for (Object toolObj : resArray) {
					JSONObject toolJsonObject = (JSONObject) toolObj;
					String idTool = toolJsonObject.get("id").toString();
					if (!(idTool.isBlank() || idTool.isEmpty())) {
						actualValidIDNumber++;
					}
					String cat = toolJsonObject.get("category").toString();
					if (cat.equals(category)) {
						actualValidCategoryNumber++;
					}
					String instock = toolJsonObject.get("inStock").toString();
					if (instock.equals(available)) {
						actualStockNumber++;
					}
				}
			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(actualToolNumber <= result);
		assertEquals(actualValidIDNumber, actualToolNumber);
		assertEquals(actualValidCategoryNumber, actualToolNumber);
		assertEquals(actualStockNumber, actualToolNumber);
	}

}