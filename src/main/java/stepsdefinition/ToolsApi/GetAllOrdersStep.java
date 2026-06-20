package stepsdefinition.ToolsApi;

import java.net.http.HttpResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetAllOrdersStep {
	String url;
	HttpResponse<String> response;
	
	@When("Reuqest to get all orders")
	public void reuqest_to_get_all_orders() {
		
	}

	@Then("Return all orders")
	public void return_all_orders() {
	}

}
