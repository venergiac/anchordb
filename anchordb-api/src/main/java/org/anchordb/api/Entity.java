package org.anchordb.api;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Basic private interface
 * @author giacomoveneri
 *
 */
public abstract interface Entity {

	public String getName();
	public void setName(String name);
	public boolean isValid();
	public void setValid(boolean valid);
	public UUID getUid();
	public Date getTimestamp();

	
   interface EntityInstance<T> extends Entity {

		public T getType();
		public void addAttribute(Attribute attribute);
		public Set<Attribute> getAttributes();
		
	}
   
   
   public static final String UOM = "uom"; 
	

}
