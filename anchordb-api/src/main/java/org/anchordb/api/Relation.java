package org.anchordb.api;

import java.util.Set;

/**
 * relation among assets
 * @author giacomoveneri
 *
 */
public interface Relation extends Entity{
	public Asset getAsset1();
	public Asset getAsset2();
	public Set<Relation> getHistory();
}
