package org.anchordb.impl.attribute;

import java.util.UUID;

import org.anchordb.api.AttributeType;

public class NumericAttribute extends AbstractAttribute<Number> {
	
	private Number value;

	public NumericAttribute(UUID uuid, String name, AttributeType type, boolean valid, Number value) {
		super(uuid, name, type, valid);
		this.value = value;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	

}
