package swingy.controller.generators;

// import Swingy.Model.entity.Villain;
// import Swingy.Model.entity.Bandit;

public class VillainGenerator {
	private static VillainGenerator instance = null;

	private VillainGenerator() {}

	public static VillainGenerator getInstance() {
		if (instance == null) {
			instance = new VillainGenerator();
		}
		return instance;
	}

	// public Villain newVillain()
}