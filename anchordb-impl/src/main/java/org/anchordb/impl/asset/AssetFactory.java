package org.anchordb.impl.asset;

import java.util.UUID;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;

public class AssetFactory {

	public static Asset createAsset(AssetClass type, String name, Asset parent) {
		GenericAsset asset = new GenericAsset(UUID.randomUUID(),
				name,
				type,
				parent,
				true);
		
		//TODO: build attributes
		
		return asset;
	}
	
	public static AssetClass createAssetClass(String name) {
		GenericAssetClass clazz = new GenericAssetClass(UUID.randomUUID(),
				name,
				true);
		
		//TODO: build attributes
		
		return clazz;
	}
}
