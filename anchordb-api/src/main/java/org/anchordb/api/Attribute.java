package org.anchordb.api;


public interface Attribute<T> extends Entity {
	
	public AttributeType getType();
	public T getValue();
	
	
}


