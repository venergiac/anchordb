package org.anchordb.impl.asset;

import java.util.Date;
import java.util.HashSet;
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
	private Set<Tag> tags;
	private Set<Attribute> attributes;
	private Set<Relation> relations;

	
	GenericAsset(UUID uuid, String name, AssetClass type, Asset parent, boolean valid) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.valid = valid;
		this.timestamp = new Date();
	}
	
	GenericAsset(UUID uuid, String name, AssetClass type, Asset parent, boolean valid, Set<Tag> tags, Set<Attribute> attributes) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.valid = valid;
		this.timestamp = new Date();
		this.attributes = attributes;
		this.tags = tags;
	}

	@Override
	public AssetClass getType() {
		return type;
	}

	@Override
	public void addAttribute(Attribute attribute) {
		if (attributes==null) attributes = new HashSet<Attribute>();
		attributes.add(attribute);
	}

	@Override
	public Set<Attribute> getAttributes() {
		return Set.copyOf(attributes);
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
		return timestamp;
	}

	@Override
	public Asset getParent() {
		return parent;
	}

	@Override
	public Set<Relation> getRelations() {
		return relations;
	}

	@Override
	public Set<Tag> getTags() {
		return tags;
	}

	@Override
	public void addTag(Tag tag) {
		if (tags==null) tags = new HashSet<Tag>();
		tags.add(tag);
	}

	@Override
	public String toString() {
		return "GenericAsset [parent=" + parent + ", uuid=" + uuid + ", type=" + type + ", name=" + name + ", valid="
				+ valid + ", timestamp=" + timestamp + "]";
	}
	
	

}
