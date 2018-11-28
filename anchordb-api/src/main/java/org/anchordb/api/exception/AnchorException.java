package org.anchordb.api.exception;

import org.anchordb.api.Entity;

/**
 * generic exception
 * @author giacomoveneri
 *
 */
public class AnchorException extends Exception {
	
	private Entity entity;

	public AnchorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AnchorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AnchorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public AnchorException(Entity entity, String operation, String cause) {
		super(String.format("Error: %s failed, due to %s for %s", operation, entity, cause));
	}

	public Entity getEntity() {
		return entity;
	}
	
	

}
