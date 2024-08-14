package swingy.controller;

public class PlayerManager {
	private static PlayerManager instance = null;

	private PlayerManager() {}

	public static PlayerManager getInstance() {
		if (instance == null) {
			instance = new PlayerManager();
		}
		return instance;
	}

	// public Player loadPlayer()
	// public Player newPlayer()
}