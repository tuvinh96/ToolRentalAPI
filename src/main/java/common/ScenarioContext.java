package common;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private Map<Context, Object> contextMap;
	public ScenarioContext() {
		this.contextMap = new HashMap<>();
	}
	public void setContext(Context key, Object value) {
		contextMap.put(key, value);
	}	
	public Object getContext(Context key) {
		return contextMap.get(key);
	}
}
