package com.stone.game;

import java.io.IOException;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.core.codec.GameCodecFactory;
import com.stone.core.config.ConfigUtil;
import com.stone.core.net.ServerProcess;
import com.stone.core.service.IService;
import com.stone.core.util.OSUtil;
import com.stone.db.DBActorSystem;
import com.stone.game.msg.ProtobufMessageFactory;

/**
 * 游戏服务器;
 * 
 * @author crazyjohn
 *
 */
public class GameServer implements IService {
	private static Logger logger = LoggerFactory.getLogger(GameServer.class);
	/** server config */
	private GameServerConfig config;
	/** config path */
	private String configPath;
	/** server io process */
	protected ServerProcess externalProcess;
	/** game actor system */
	protected GameActorSystem gameActorSystem;
	/** db actor system */
	protected DBActorSystem dbActorSystem;

	/**
	 * new game server instance;
	 * 
	 * @param configPath
	 *            the config file path;
	 */
	public GameServer(String configPath) {
		this.configPath = configPath;
	}

	@Override
	public void init() throws ScriptException, IOException {
		config = new GameServerConfig();
		ConfigUtil.loadJsConfig(config, configPath);
		gameActorSystem = GameActorSystem.getInstance();
		gameActorSystem.initSystem(config.getGameProcessorCount());
		dbActorSystem = DBActorSystem.getInstance();
		// init db system
		dbActorSystem.initSystem(config.getDbProcessorCount());
		dbActorSystem.initDBService(config.getDbServiceType(), config.getDbConfigName(), config.getDataServiceProperties());
		// 对外服务
		externalProcess = new ServerProcess(config.getBindIp(), config.getPort(), new GameIoHandler(gameActorSystem), new GameCodecFactory(new ProtobufMessageFactory()));

	}

	@Override
	public void start() throws IOException {
		logger.info("Begin to start main dispatcher...");
		gameActorSystem.start();
		logger.info("Main dispatcher started.");
		logger.info("Begin to start db dispatcher...");
		dbActorSystem.start();
		logger.info("DB dispatcher started.");
		logger.info("Begin to start Server Process...");
		externalProcess.start();
		logger.info("Server Process started.");
		// shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				shutdown();
			}
		}));
		// safe debug shutdown
		if (this.config.getIsDebug()) {
			addSafeDebugWorkFollow();
		}
	}

	/**
	 * Add safe debug work follow;
	 */
	private void addSafeDebugWorkFollow() {
		if (OSUtil.isWindowsOS()) {
			Thread winDeamon = new Thread() {
				@Override
				public void run() {
					try {
						System.in.read();
						shutdown();
					} catch (IOException e) {
						logger.error("Wait shutdown error", e);
					}
				}
			};
			winDeamon.setDaemon(true);
			winDeamon.setName("WindowDebugGuarder");
			winDeamon.start();
		}
	}

	@Override
	public void shutdown() {
		externalProcess.shutdown();
		dbActorSystem.stop();
		gameActorSystem.stop();
	}

	public static void main(String[] args) {
		logger.info("Begin to start Game Server...");
		try {
			GameServer gameserver = new GameServer("game_server.cfg.js");
			gameserver.init();
			gameserver.start();
			logger.info("Game Server started.");
		} catch (Exception e) {
			logger.error("Failed to start server.", e);
			System.exit(0);
		}

	}

}
