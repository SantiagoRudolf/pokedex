import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	int id, evoLvl, onFound;
	Pokemon  evolution;
	String name;
	List<Ability> abilities;
	List<Type> types;
	
	public Pokemon(String name, int id, int evoLvl, int onFound) {
		super();
		this.id = id;
		this.evolution = new Pokemon();
		this.evoLvl = evoLvl;
		this.onFound = onFound;
		this.name = name;
		this.abilities = new ArrayList<Ability>();
		this.types = new ArrayList<Type>();
	}
	public List<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}
	public List<Type> getTypes() {
		return types;
	}
	public void setTypes(List<Type> types) {
		this.types = types;
	}
	public Pokemon() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pokemon getEvolution() {
		return evolution;
	}
	public void setEvolution(Pokemon evolution) {
		this.evolution = evolution;
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
