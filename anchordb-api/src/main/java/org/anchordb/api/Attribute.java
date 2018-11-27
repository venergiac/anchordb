package org.anchordb.api;


/**
 * Attribute API. Can be historycal
 * @author giacomoveneri
 *
 * @param <T>
 */
public interface Attribute<T> extends Entity {
	
	public AttributeType getType();
	public T getValue();
	public void setValue(T value);
}


