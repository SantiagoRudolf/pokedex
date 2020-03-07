
public class Pokemon {
	int id, evolution, types, abilities, evoLvl, onFound;
	String name;
	
	public Pokemon(String name, int id, int evolution, int types, int abilities, int evoLvl, int onFound) {
		super();
		this.id = id;
		this.evolution = evolution;
		this.types = types;
		this.abilities = abilities;
		this.evoLvl = evoLvl;
		this.onFound = onFound;
		this.name = name;
	}
	public Pokemon() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEvolution() {
		return evolution;
	}
	public void setEvolution(int evolution) {
		this.evolution = evolution;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public int getAbilities() {
		return abilities;
	}
	public void setAbilities(int abilities) {
		this.abilities = abilities;
	}
	public int getEvoLvl() {
		return evoLvl;
	}
	public void setEvoLvl(int evoLvl) {
		this.evoLvl = evoLvl;
	}
	public int getOnFound() {
		return onFound;
	}
	public void setOnFound(int onFound) {
		this.onFound = onFound;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
