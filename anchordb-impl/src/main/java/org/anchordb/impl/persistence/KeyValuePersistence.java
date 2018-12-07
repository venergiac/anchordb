package org.anchordb.impl.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.anchordb.api.Asset;
import org.anchordb.api.AssetClass;
import org.anchordb.api.Attribute;
import org.anchordb.api.Entity;
import org.anchordb.api.Tag;
import org.anchordb.api.exception.AnchorException;
import org.anchordb.api.persistence.PersistenceService;
import org.anchordb.impl.asset.AssetFactory;

public class KeyValuePersistence implements PersistenceService<Query, Entity> {

	Map<UUID, AssetClass> assetClasses = new HashMap<>();
	Map<UUID, Asset> assets = new HashMap<>();
	Map<UUID, Attribute> attributes = new HashMap<>();
	Map<UUID, Tag> tags = new HashMap<>();

	Map<UUID, UUID> childsAsset = new HashMap<>();
	Map<UUID, Entry<UUID, UUID>> relations = new HashMap<>();
	Map<UUID, UUID> tagsAsset = new HashMap<>();
	Map<UUID, UUID> attributesAsset = new HashMap<>();

	@Override
	public Entity find(Query q) throws AnchorException {
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public UUID create(Entity entity) throws AnchorException {

		try {
			applyIfInstanceOf(Tag.class, x -> createObject(x)).accept(entity);

			applyIfInstanceOf(AssetClass.class, x -> createObject(x)).accept(entity);

			applyIfInstanceOf(Asset.class, x -> createObject(x)).accept(entity);

			applyIfInstanceOf(Attribute.class, x -> createObject(x)).accept(entity);
		} catch (Error e) {
			throw new AnchorException(entity, "create", e.getMessage());
		}

		return null;
	}

	public UUID createObject(Tag c) {

		return null;
	}

	public UUID createObject(Attribute c) {
		return null;
	}

	public UUID createObject(AssetClass c) {
		assetClasses.put(c.getUid(), c);
		return c.getUid();
	}

	public UUID createObject(Asset a) {

		// parent UUID
		UUID pUid = Optional.ofNullable(a.getParent()).map(x -> x.getUid()).orElse(null);

		// check if parent exists
		Optional.ofNullable(pUid).ifPresent(p -> Optional.of(get(p)));

		// save relations
		Optional.ofNullable(pUid).ifPresent(p -> childsAsset.put(a.getUid(), p));
		System.out.println(childsAsset);

		// save attributes
		// TODO

		// save tags
		// TODO

		// finally save object
		assets.put(a.getUid(), a);

		return a.getUid();
	}

	@Override
	public Entity update(Entity n) throws AnchorException {
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public void delete(Entity entity) throws AnchorException {

		Entity e = Optional.of(get(entity.getUid()))
				.orElseThrow(() -> new AnchorException(entity, "deletion", "doesn't exist"));

		try {
			applyIfInstanceOf(Asset.class, x -> deleteObject(x)).accept(entity);

			applyIfInstanceOf(Tag.class, x -> deleteObject(x)).accept(entity);

			applyIfInstanceOf(AssetClass.class, x -> deleteObject(x)).accept(entity);

			applyIfInstanceOf(Attribute.class, x -> deleteObject(x)).accept(entity);
		} catch (Throwable ex) {
			throw new AnchorException(entity, "deletion", ex.getMessage());
		}

	}

	private void deleteObject(final Asset asset) {

		// check if any relation as parent
		Optional.of(getChildsUid(asset)).filter(p -> p != null && !p.isEmpty()).ifPresent(a -> {
			throw new Error("is a parent asset");
		});

		// remove relations
		// TODO: relations unsupported

		// remove parent child relation
		childsAsset.remove(asset.getUid());

		// remove all attributes
		// TODO: relations unsupported

		// remove all tags
		// TODO: relations unsupported

		// now remove asset
		assets.remove(asset.getUid());

	}

	private void deleteObject(Attribute attribute) {
		
		attributesAsset.remove(attribute.getUid());
		attributes.remove(attribute.getUid());
	}

	private void deleteObject(Tag tag) {
		tagsAsset.remove(tag.getUid());
		tags.remove(tag.getUid());
	}

	private void deleteObject(AssetClass clzz) {

		assets.values().stream().filter(a -> a.getType().getUid().equals(clzz.getUid())).map(a -> {throw new Error(" class of " + a);});
		assetClasses.remove(clzz.getUid());
	}

	@Override
	public Entity get(UUID id) {

		Asset asset = assets.get(id);
		if (asset != null)
			return build(asset);

		Tag tag = tags.get(id);
		if (tag != null)
			return build(tag);

		Attribute attribute = attributes.get(id);
		if (attribute != null)
			return build(attribute);

		AssetClass assetClass = assetClasses.get(id);
		if (assetClass != null)
			return build(assetClass);

		return null;
	}

	private Asset build(Asset asset) {

		//build with attributes
		Set<Attribute> attributes = (Set<Attribute>)getSubObjects(this.attributesAsset, this.attributes, asset);
		
		// build with tags
		Set<Tag> tags = (Set<Tag>)getSubObjects(this.tagsAsset, this.tags, asset);
		
		// build parent child relation
		Asset parent = Optional.ofNullable(getParentUid(asset)).map( u-> (Asset)get(u) ).orElse(null);

		// build with relations
		AssetFactory.copyAsset(asset, tags, attributes);

		
		return asset;
	}

	private Tag build(Tag tag) {
		return tag;
	}

	private Attribute build(Attribute asset) {
		return asset;
	}

	private AssetClass build(AssetClass asset) {
		return asset;
	}

	private UUID getParentUid(final Asset asset) {
		return childsAsset.get(asset.getUid());
	}

	private Set<UUID> getChildsUid(final Asset asset) {
		return filterByAsset(childsAsset,asset);
	}
	
	private Set<? extends Entity> getSubObjects(final Map<UUID,UUID> map, Map<UUID,? extends Entity> entyties, final Asset asset) {
		Set<UUID> uuids = filterByAsset(attributesAsset,asset);
		return entyties.entrySet().stream().filter(e -> uuids.contains( e.getKey())).map(e -> e.getValue() ).collect(Collectors.toSet());
	}
	
	private Set<UUID> filterByAsset(final Map<UUID,UUID> map, final Asset asset) {
		return map.values().stream().filter(u -> u.equals(asset.getUid())).collect(Collectors.toSet());
	}
	
	private static <T> Consumer applyIfInstanceOf(Class<T> cls, Consumer<T> c) {
		return obj -> Optional.of(obj).filter(cls::isInstance).map(cls::cast).ifPresent(c);
	}

}
