package rog.anchordb.impl.test;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Entity;
import org.anchordb.api.persistence.PersistenceService;
import org.anchordb.impl.asset.AssetFactory;
import org.anchordb.impl.persistence.KeyValuePersistence;
import org.anchordb.impl.persistence.Query;
import org.junit.After;

public class TestPersistence {

	
	PersistenceService<Query, Entity> service;
	
	@After
	public void setUp() throws Exception {
		service = new KeyValuePersistence();
		
		AssetClass clazz = AssetFactory.createAssetClass("basic");
		service.create(clazz);
		
		
		Asset assetR = AssetFactory.createAsset(clazz, "root", null);
		service.create(assetR);
		
		Asset assetC = AssetFactory.createAsset(clazz, "root",assetR);
		service.create(assetC);
	}
	
}
