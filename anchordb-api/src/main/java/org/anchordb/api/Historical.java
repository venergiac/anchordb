package org.anchordb.api;

import java.util.Map;

public interface Historical<T>
{
	
	
	public final String NO_VERSION = "0";
	
	public void tagVersion(String name);
	public Map<String,T> getHistory();
	public String getVersion();
}
