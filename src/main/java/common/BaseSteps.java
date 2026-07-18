package common;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class BaseSteps {
	public String url;
	public String method;
	Map<String, String> headers = new HashMap<String, String>();
	private ScenarioContext scenarioContext;
//	@Given("I have header")
//	public void i_have_header(DataTable headerTable) {
//		List<Map<String, String>> originalHeaders = headerTable.asMaps(String.class, String.class);
//		for (Map<String, String> header : originalHeaders) {
//			String key = header.get("key");
//			String value = header.get("value");
//			headers.put(key,value);
//		}
//		scenarioContext.setContext("headers", headers);
//	}
//	
//	@Given("I have url and method")
//	public void i_have_url_and_method(DataTable urlAndMethodTable) {
//		List<Map<String, String>> originalUrlMethods = urlAndMethodTable.asMaps(String.class, String.class);
//		url = originalUrlMethods.get(0).get("url");
//		scenarioContext.setContext("url",url);
//		method = originalUrlMethods.get(0).get("method");
//		scenarioContext.setContext("method", method);
//	}
}
