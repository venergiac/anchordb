package org.anchordb.impl.asset;

import java.util.UUID;

import org.anchordb.api.AssetClass;

public class GenericAssetClass extends GenericAsset implements AssetClass{

	public GenericAssetClass(UUID uuid, String name, boolean valid) {
		super(uuid, name, null, null, valid);
	}

}
