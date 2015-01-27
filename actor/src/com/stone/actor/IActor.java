package com.stone.actor;

/**
 * Actor接口;
 * 
 * @author crazyjohn
 *
 */
public interface IActor {

	public void put(IActorCall call);

	public void put(IActorCall call, IActorCallback callback, IActorId source);

	public void run();

	public void start();

	public void stop();

	public void put(IActorCallback callback);

}