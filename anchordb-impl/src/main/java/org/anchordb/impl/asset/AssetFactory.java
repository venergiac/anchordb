package org.anchordb.impl.asset;

import java.util.Set;
import java.util.UUID;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Attribute;
import org.anchordb.api.Tag;

public class AssetFactory {

	public static Asset createAsset(AssetClass type, String name, Asset parent) {
		GenericAsset asset = new GenericAsset(UUID.randomUUID(),
				name,
				type,
				parent,
				true);
		
		return asset;
	}
	
	public static Asset createAsset(AssetClass type, String name, Asset parent, Set<Tag> tags, Set<Attribute> attributes) {
		GenericAsset asset = new GenericAsset(UUID.randomUUID(),
				name,
				type,
				parent,
				true,
				tags, 
				attributes);
		
		return asset;
	}
	
	public static Asset copyAsset(Asset source, Set<Tag> tags, Set<Attribute> attributes) {
		GenericAsset asset = new GenericAsset(source.getUid(),
				source.getName(),
				source.getType(),
				source.getParent(),
				source.isValid(),
				tags, 
				attributes);
		
		return asset;
	}
	
	public static AssetClass createAssetClass(String name) {
		GenericAssetClass clazz = new GenericAssetClass(UUID.randomUUID(),
				name,
				true);
		
		return clazz;
	}
}
