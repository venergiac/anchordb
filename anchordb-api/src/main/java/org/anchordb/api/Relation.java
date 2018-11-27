package org.anchordb.api;


/**
 * relation among assets
 * @author giacomoveneri
 *
 */
public interface Relation extends Entity{
	public Asset getAsset1();
	public Asset getAsset2();

}
