package org.anchordb.impl.attribute;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.anchordb.api.Attribute;
import org.anchordb.api.AttributeType;
import org.anchordb.api.Historical;


abstract class AHistoricalAttribute<T> extends ABasicAttribute<T> implements Attribute<T>,Historical<Attribute<T>> {


	protected String version;
	private Map<String, Attribute<T>> history = new HashMap<String, Attribute<T>>();


	protected AHistoricalAttribute(UUID uuid, AttributeType type, String name, boolean valid, Date timestamp) {
		super(uuid, type, name, valid,timestamp);
		this.version = NO_VERSION;
	}




	@Override
	public Map<String, Attribute<T>> getHistory() {
		if (NO_VERSION.equalsIgnoreCase(version)) return null;
		return history;
	}


	@Override
	public String getVersion() {
		return version;
	}


	@Override
	public void tagVersion(String name) {
		if (AttributeType.Shared.equals(this.type)) {
			throw new UnsupportedOperationException("NOT YET IMPLEMEMNTED");
		} else {
			Attribute cloned = AttributeFactory.createAttribute(this.type, name, false);
			cloned.setValue(this.getValue());
			history.put(name, cloned);
		}
		
	}




}
