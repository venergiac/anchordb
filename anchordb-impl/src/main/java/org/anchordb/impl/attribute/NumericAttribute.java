package org.anchordb.impl.attribute;

import java.util.Date;
import java.util.UUID;

import org.anchordb.api.AttributeType;

public class NumericAttribute extends AHistoricalAttribute<Number> {
	
	private Number value;
	
	public NumericAttribute(UUID uuid, AttributeType type, String name, boolean valid, Date timestamp, Number value) {
		super(uuid, type, name, valid, timestamp);
		this.value = value;
	}


	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	

}
