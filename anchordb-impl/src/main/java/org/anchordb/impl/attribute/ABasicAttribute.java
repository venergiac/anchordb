package org.anchordb.impl.attribute;

import java.util.Date;
import java.util.UUID;

import org.anchordb.api.Attribute;
import org.anchordb.api.AttributeType;

public abstract class ABasicAttribute<T> implements Attribute<T> {
	
	protected UUID uuid;
	protected AttributeType type;
	protected String name;
	protected boolean valid;
	protected Date timestamp;
	
	protected ABasicAttribute(UUID uuid, AttributeType type, String name, boolean valid, Date timestamp) {
		super();
		this.uuid = uuid;
		this.type = type;
		this.name = name;
		this.valid = valid;
		this.timestamp = timestamp;
	}


	@Override
	public UUID getUid() {
		return uuid;
	}


	@Override
	public AttributeType getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean isValid() {
		return valid;
	}


	@Override
	public void setValid(boolean valid) {
		this.valid = valid;
	}


	@Override
	public Date getTimestamp() {
		return timestamp;
	}
}
