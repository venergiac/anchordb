package org.anchordb.api;

import java.util.Set;

import org.anchordb.api.Entity.EntityInstance;


public interface Asset extends EntityInstance<AssetClass> {

	public Asset getParent();

	public Set<Relation> getRelations();

	public Set<Tag> getTags();
	
	

}
