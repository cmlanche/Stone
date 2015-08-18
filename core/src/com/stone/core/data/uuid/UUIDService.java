package com.stone.core.data.uuid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import akka.actor.Props;

import com.stone.core.annotation.ActorMethod;
import com.stone.core.concurrent.annotation.ThreadSafeUnit;
import com.stone.core.db.service.IDBService;

/**
 * The uuidService;
 * <p>
 * Maybe a singletone or just a actor?
 * 
 * @author crazyjohn
 *
 */
@ThreadSafeUnit
public class UUIDService implements IUUIDService {
	protected IDBService dbService;
	private Map<UUIDType, IUUID64> uuids;

	public static IUUIDService buildUUIDService(IDBService dbService, UUIDType[] types, int regionId, int serverId) {
		IUUIDService result = new UUIDService(dbService, types, regionId, serverId);
		return result;
	}

	private UUIDService(IDBService dbService, UUIDType[] types, int regionId, int serverId) {
		this.dbService = dbService;
		uuids = new ConcurrentHashMap<UUIDType, IUUID64>();
		for (UUIDType eachType : types) {
			// query max id from db
			int maxId = queryMaxIdFromDB(eachType);
			uuids.put(eachType, UUID64.buildDefaultUUID(regionId, serverId, maxId));
		}

	}

	@ActorMethod(messageClassType = UUIDType.class)
	protected void handleUUIDRequest(Object msg) {
		if (msg instanceof UUIDType) {
			// getSender().tell(getNextId((UUIDType) msg), getSelf());
		}

	}

	private int queryMaxIdFromDB(UUIDType eachType) {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public long getNextId(UUIDType uuidType) {
		IUUID64 uuid = this.uuids.get(uuidType);
		return uuid.getNextId();
	}

	public static Props props(IDBService dbService, UUIDType[] types, int regionId, int serverId) {
		return Props.create(UUIDService.class, dbService, types, regionId, serverId);
	}
}