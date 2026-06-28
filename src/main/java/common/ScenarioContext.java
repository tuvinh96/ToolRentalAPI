package common;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private Map<String, Object> context = new HashMap<>();
	public void setContext(String key, Object value) {
		context.put(key, value);
	}
	public Object getContextByKey(String key) {
		return context.get(key);
	}
}
