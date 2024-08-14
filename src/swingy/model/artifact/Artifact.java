package swingy.model.artifact;

public class Artifact {
	private String name; // mehhh
	private int attack;
	private int defense;
	private int hitPoints;

	private String type;
	private int quality;

	public Artifact(String p_name, int p_attack, int p_defense, int p_hitPoints) {
		this.name = p_name;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hitPoints = p_hitPoints;
	}

	//GETTERS
	public String getName() {
		return name;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	//SETTERS
	public void setName(String p_name) {
		this.name = p_name;
	}

	public void setAttack(int p_attack) {
		this.attack = p_attack;
	}

	public void setDefense(int p_defense) {
		this.defense = p_defense;
	}

	public void setHitPoints(int p_hitPoints) {
		this.hitPoints = p_hitPoints;
	}
}