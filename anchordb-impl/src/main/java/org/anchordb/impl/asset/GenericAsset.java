package org.anchordb.impl.asset;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Attribute;
import org.anchordb.api.Relation;
import org.anchordb.api.Tag;

public class GenericAsset implements Asset {

	private Asset parent;
	private UUID uuid;
	private AssetClass type;
	private String name;
	private boolean valid;
	private Date timestamp;

	
	
	
	GenericAsset(UUID uuid, String name, AssetClass type, Asset parent, boolean valid) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.valid = valid;
		this.timestamp = new Date();
	}

	@Override
	public AssetClass getType() {
		return type;
	}

	@Override
	public void addAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Attribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public void setValid(boolean valid) {
		this.valid=valid;
		
	}

	@Override
	public UUID getUid() {
		return uuid;
	}

	@Override
	public Date getTimestamp() {
		return null;
	}

	@Override
	public Asset getParent() {
		return parent;
	}

	@Override
	public Set<Relation> getRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTag(Tag tag) {
		// TODO Auto-generated method stub
		
	}

}
