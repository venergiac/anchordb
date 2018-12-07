package org.anchordb.api.persistence;

import java.util.UUID;

import org.anchordb.api.Entity;
import org.anchordb.api.exception.AnchorException;

/** 
 *  CRUD interface
 * @author giacomoveneri
 *
 * @param <Q>
 * @param <N>
 */
public interface PersistenceService<Q, N extends Entity> {

	public N find(Q q) throws AnchorException;
	public UUID create(N n) throws AnchorException;
	public N update(N n) throws AnchorException;
	public void delete(N n) throws AnchorException;
	public N get(UUID id);
	
}
