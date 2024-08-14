package swingy.controller.generators;

// import Swingy.Model.artifact.Artifact;
// import Swingy.Model.artifact.Weapon;
// import Swingy.Model.artifact.Armor;
// import Swingy.Model.artifact.Helm;

public class ArtifactGenerator {
	private static ArtifactGenerator instance = null;

	private ArtifactGenerator() {}

	public static ArtifactGenerator getInstance() {
		if (instance == null) {
			instance = new ArtifactGenerator();
		}
		return instance;
	}

	// public Artifact newArtifact()
}