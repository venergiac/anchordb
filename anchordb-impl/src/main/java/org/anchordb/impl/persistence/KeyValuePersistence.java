package org.anchordb.impl.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Attribute;
import org.anchordb.api.Entity;
import org.anchordb.api.Tag;
import org.anchordb.api.exception.AnchorException;
import org.anchordb.api.persistence.PersistenceService;

public class KeyValuePersistence implements PersistenceService<Query, Entity>{
	
	Map<UUID, AssetClass> assetClasses = new HashMap<>();
	Map<UUID, Asset> assets = new HashMap<>();
	Map<UUID, Attribute> attributes = new HashMap<>();
	Map<UUID, Tag> tags = new HashMap<>();
	
	Map<UUID, UUID> childsParent = new HashMap<>();
	Map<UUID, Entry<UUID,UUID>> relations = new HashMap<>();
	Map<UUID, UUID> tagsAsset = new HashMap<>();
	Map<UUID, UUID> attributesAsset = new HashMap<>();
	

	@Override
	public Entity find(Query q) throws AnchorException {
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public UUID create(Entity n) throws AnchorException {
		
		return null;
	}
	
	public UUID createObject(AssetClass c) throws AnchorException {
		assetClasses.put(c.getUid(), c);
		return c.getUid();
	}

	public UUID createObject(Asset a) throws AnchorException {
	
		return null;
	}

	@Override
	public Entity update(Entity n) throws AnchorException {
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public void delete(Entity entity) throws AnchorException {
		//deleteObject(entity);	
/*
			switchType(entity,
						caze(Asset.class, consumerWrapper(e-> this.deleteObject(e), AnchorException.class)),
				       caze(Attribute.class, e-> this.deleteObject(e)),
				       caze(Tag.class, e-> this.deleteObject(e)),
				       caze(AssetClass.class, e-> this.deleteObject(e))
				);
*/
	}
	
	
	
	private UUID getParentUid(Asset asset) {
		return childsParent.get(asset.getUid());
	}
	
	private Set<UUID> getChildsUid(Asset asset) {
		return childsParent.values().stream()
			.filter( u -> u.equals(asset.getUid()) )
				.collect(Collectors.toSet());
	}
	
	/**
	private UUID getRealatedUid(Asset asset) {
		return Optional.of(
					relations.get(asset.getUid())
				).orElse(
					relations.values().stream().filter(u -> u.equals(asset.getUid())).findFirst().get() 
				);
	}**/
	
	private void deleteObject(final Asset asset) throws AnchorException {
		
		// check if any relation as parent
		Optional.of(getChildsUid(asset))
			.orElseThrow(()->new AnchorException(asset, "deletion", "is a parent asset"));
		
		//check if any relation
		
		
	}
	
	private void deleteObject(Attribute attribute) {
		
	}
	
	private void deleteObject(Tag tag) {
		
	}
	
	private void deleteObject(AssetClass tag) {
		
	}

	@Override
	public Entity get(UUID id) throws AnchorException {
		Asset asset = assets.get(id);
		if (asset!=null) return asset;
		
		Tag tag = tags.get(id);
		if (tag!=null) return tag;
		
		Attribute attribute = attributes.get(id);
		if (attribute!=null) return attribute;
		
		AssetClass assetClass = assetClasses.get(id);
		if (assetClass!=null) return assetClass;
		
		return null;
	}
	
	
	
	private static <T> void switchType(Object o, Consumer<T>... a) {
	    for (Consumer consumer : a)
			consumer.accept(o);
	    
	}

	private static <T> Consumer caze(Class<T> cls, Consumer<T> c) {
	    return obj -> Optional.of(obj).filter(cls::isInstance).map(cls::cast).ifPresent(c);
	}
	
	private static <T, E extends Exception> Consumer<T>
	  consumerWrapper(Consumer<T> consumer, Class<E> clazz) {
	  
	    return i -> {
	        try {
	            consumer.accept(i);
	        } catch (Exception ex) {
	            try {
	                E exCast = clazz.cast(ex);
	                System.err.println(
	                  "Exception occured : " + exCast.getMessage());
	            } catch (ClassCastException ccEx) {
	                throw ex;
	            }
	        }
	    };
	}

}
