import java.util.ArrayList;
import java.util.List;

public class User {
	List<Pokemon> ownPokemons;
	int id;
	String name;
	
	public User() {
		ownPokemons = new ArrayList<Pokemon>();
	}
	public User(int id, String name) {
		ownPokemons = new ArrayList<Pokemon>();
		this.id = id;
		this.name = name;
	}
	public List<Pokemon> getOwnPokemons() {
		return ownPokemons;
	}
	public void setOwnPokemons(List<Pokemon> ownPokemons) {
		this.ownPokemons = ownPokemons;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
