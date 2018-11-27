package org.anchordb.impl.asset;

import java.util.UUID;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;

public class AssetFactory {

	public Asset createAsset(AssetClass type, String name) {
		return new GenericAsset(UUID.randomUUID(),
				name,
				type,
				null,
				true);
	}
}
