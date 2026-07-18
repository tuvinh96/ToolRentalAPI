package stepsdefinition.ToolsApi;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.Context;
import common.JsonUtils;
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
	String category;
	int result;
	String available;
	HttpResponse<String> response;
	Map<String, String> headers = new HashMap<String, String>();
	private final ScenarioContext scenarioContext;

	public CheckResponseWhenSendRequestSuccessfullySteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;

	}

	@Given("I have header")
	public void i_have_header(DataTable headerTable) {
		List<Map<String, String>> originalHeaders = headerTable.asMaps(String.class, String.class);
		for (Map<String, String> header : originalHeaders) {
			String key = header.get("key");
			String value = header.get("value");
			headers.put(key, value);
		}
		scenarioContext.setContext(Context.HEADERS, headers);
	}

	@Given("I have url and method")
	public void i_have_url_and_method(DataTable urlAndMethodTable) {
		List<Map<String, String>> originalUrlMethods = urlAndMethodTable.asMaps(String.class, String.class);
		url = originalUrlMethods.get(0).get("url");
		scenarioContext.setContext(Context.URL, url);
		method = originalUrlMethods.get(0).get("method");
		scenarioContext.setContext(Context.METHOD, method);
	}

	@Given("I have {string} and {string} of tools and {string} status")
	public void i_have_and_of_tools_and_status(String givenCategory, String givenResult, String givenAvailable) {
		category = givenCategory;
		result = Integer.parseInt(givenResult);
		available = givenAvailable;
		String newUrl = url.replace("@category", givenCategory).replace("@results", givenResult).replace("@available",
				givenAvailable);
		scenarioContext.setContext(Context.URL, newUrl);
	}

	@When("send request")
	public void send_request_with_valid_url_and_method_and_params() {
		String newUrl = scenarioContext.getContext(Context.URL).toString();
		String newMethod = scenarioContext.getContext(Context.METHOD).toString();
		Map<String, String> newHeaders = (Map<String, String>) scenarioContext.getContext(Context.HEADERS);
		RequestUtils req = new RequestUtils();
		response = req.sendRequest(newUrl, newMethod, newHeaders, "");

	}

	@Then("Api responds status code {string}")
	public void api_responds_status_code(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
		
	}
	
	@Then("Api responds list of tools correctly")
	public void api_responds_list_of_tools() {
		String responseJson = response.body();
		JsonUtils jsonUtils = new JsonUtils();
		ArrayList<String> ids = jsonUtils.getDataByKey(responseJson, "id");
		ArrayList<String> cats = jsonUtils.getDataByKey(responseJson, "category");
		ArrayList<String> instocks = jsonUtils.getDataByKey(responseJson, "inStock");

		assertTrue(ids.size() <= result);
		assertEquals(cats.size(), ids.size());
		assertEquals(instocks.size(), ids.size());
		boolean isCatChecked = true;
		for (String cat : cats) {
			if (!cat.equals(category)) {
				isCatChecked = false;
				break;
			}
		}
		assertTrue(isCatChecked);
		boolean isStockChecked = true;
		for (String stock : instocks) {
			if (!stock.equals(available)) {
				isStockChecked = false;
				break;
			}
		}
		assertTrue(isStockChecked);
	}
}