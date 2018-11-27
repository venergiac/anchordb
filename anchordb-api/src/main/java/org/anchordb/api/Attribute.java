package org.anchordb.api;

import java.util.Set;

/**
 * Attribute API. Can be historycal
 * @author giacomoveneri
 *
 * @param <T>
 */
public interface Attribute<T> extends Entity {
	
	public AttributeType getType();
	public T getValue();
	public Set<Attribute<T>> getHistory();
	
}


