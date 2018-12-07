package rog.anchordb.impl.test;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Entity;
import org.anchordb.api.exception.AnchorException;
import org.anchordb.api.persistence.PersistenceService;
import org.anchordb.impl.asset.AssetFactory;
import org.anchordb.impl.persistence.KeyValuePersistence;
import org.anchordb.impl.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestPersistence {

	PersistenceService<Query, Entity> service;
	
	Asset assetR=null;
	Asset assetC=null;

	
	@BeforeEach
	public void setUp() throws Exception {
		service = new KeyValuePersistence();
		
		AssetClass clazz = AssetFactory.createAssetClass("basic");
		service.create(clazz);
		
		assetR = AssetFactory.createAsset(clazz, "root", null);
		service.create(assetR);
		
		assetC = AssetFactory.createAsset(clazz, "child",assetR);
		service.create(assetC);
	}
	
	@Test
	@DisplayName("Retrieving the ROOT")
	public void testGetRoot() throws Exception {
		Asset assetCopy = (Asset)service.get(assetR.getUid());
		assertEquals(assetCopy, assetR);
	}
	
	
	@Test
	@DisplayName("Deleting a root with child should cause error")
	public void testDeleteRoot() throws Exception {
		System.out.println(assetR);
		assertThrows(AnchorException.class, () -> service.delete(assetR));
	}
	
	@Test
	@DisplayName("Delete asset with no child")
	public void testDeleteChild() {
		assertAll( 
					() -> service.delete(assetC) ,
					() -> assertNull( service.get(assetC.getUid()))
					
				);
	}
	
	@Test
	@DisplayName("Add assets and relations")
	public void testRelation() {
		
	}
	
	@Test
	@DisplayName("Add tags to an asset, delete asset and check tags")
	public void testTag() {
		
	}
	
	@Test
	@DisplayName("Update tag of an asset, retrieve asset and get tag")
	public void updateTag() {
		
	}
	
}
