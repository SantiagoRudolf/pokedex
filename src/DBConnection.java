
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DBConnection {
    public static Connection con = null;
    static Scanner console = new Scanner(System.in);
	public DBConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    String connectionURL="jdbc:sqlserver://RUDOLF\\SQLEXPRESS:1433;databaseName=pokedex;user=certantuser;password=123";
		try {
			DBConnection.con=DriverManager.getConnection(connectionURL);
			
		} catch(SQLException e){

   	     System.out.println(e);

   	    }
	}
	public static Connection getCon() {
		return con;
	}
	
	public static void main(String args[ ]){
		
	}
    
	//methods for types
    public List<Type> getTypes() throws ClassNotFoundException, SQLException {

    	    List<Type> types = new ArrayList<Type>();
    	    CallableStatement stmt = null;
    	    ResultSet rs = null;
    	    Connection con = getCon();

    	    
    	    if (con != null){
    	    	try{
	    	        stmt = con.prepareCall("{call getAllTypes}");
	    	        rs = stmt.executeQuery();
	    	        while (rs.next()) {
	    	            types.add(new Type(rs.getInt(1),rs.getString(2)));
	    	        }
    	    	}catch(SQLException e){
    	    		System.out.println(e);
    	    	}
    	        return types;
    	    } else {
    	    	System.out.println("No se pudo establecer coneccion con la base de datos");
    	    	return types;
    	    	}
    	    }
    
    public boolean newType(Type t) throws ClassNotFoundException, SQLException {
    
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call insertType(?)}"); // se prepara para llamar al SP insertType
    	        stmt.setString(1, t.getName()); // se agrega la variable nombre del nuevo tipo 
    	        stmt.executeUpdate();
    	        
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
	}
    
    public boolean deleteType(Type t) throws ClassNotFoundException, SQLException {
        
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deleteType(?)}"); // se prepara para llamar al SP insertType
    	        stmt.setString(1, t.getName()); // se agrega la variable nombre del nuevo tipo 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
	}

    public Type getTypeByName(String name) throws ClassNotFoundException, SQLException {
    	Type type = new Type();
    	CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call getTypeByName(?)}");
    	        stmt.setString(1, name);
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	type = new Type(rs.getInt(1),rs.getString(2));
    	        }
    	    if (type.getName() != null) {
    	    	return type;
    	    }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    }
		return type;
    }
    
    //methods for abilities
    public List<Ability> getAllAbilities() throws ClassNotFoundException, SQLException {
    	List<Ability> abilities = new ArrayList<Ability>();
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();

	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call getAllAbilities}");
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	abilities.add(new Ability(rs.getInt(1),rs.getString(2)));
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	        return abilities;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return abilities;
	    	}    	
    }

    public boolean newAbility(Ability a) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call insertAbility(?)}");
    	        stmt.setString(1, a.getAbility()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    public boolean deleteAbility(Ability a) throws ClassNotFoundException, SQLException {
        
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deleteAbility(?)}");
    	        stmt.setString(1, a.getAbility());
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
	}

    public Ability getAbilityByName(String name) throws ClassNotFoundException, SQLException {
    	Ability ability = new Ability();
    	CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call getAbilityByName(?)}");
    	        stmt.setString(1, name);
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	ability = new Ability(rs.getInt(1),rs.getString(2));
    	        }
    	    if (ability.getAbility() != null) {
    	    	return ability;
    	    }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    }
		return ability;
    }
       
    //methos for pokemons
    public Pokemon getPokemonByName(String name) throws ClassNotFoundException, SQLException{
    	Pokemon poke = new Pokemon();
    	List<Ability> abilities;
    	List<Type> types;
    	CallableStatement stmt = null;
    	CallableStatement astmt = null;
	    ResultSet rs = null;
	    ResultSet ars= null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		stmt = con.prepareCall("{call getPokemonByName(?)}");
    	        stmt.setString(1, name); 
    	        rs = stmt.executeQuery();
    	        while (rs.next() && rs.getString(1) != null) {
    	        	poke = new Pokemon(rs.getString(1), rs.getInt(2), rs.getInt(4),rs.getInt(5));
    	        	poke.setEvolution(getPokemonById(rs.getInt(3)));
    	        	abilities = new ArrayList<Ability>();
    	        	astmt = con.prepareCall("{call getPokemonAbilities(?)}");
    	        	astmt.setInt(1, poke.getId());
    	        	ars = astmt.executeQuery();
    	        	while (ars.next()) {
        	        	abilities.add(new Ability(ars.getInt(1),ars.getString(2)));
        	        }
    	        	ars = null;
    	        	astmt = null;
    	        	poke.setAbilities(abilities);
    	        	types = new ArrayList<Type>();
    	        	astmt = con.prepareCall("{call getPokemontypes(?)}");
    	        	astmt.setInt(1, poke.getId());
    	        	ars = astmt.executeQuery();
    	        	while (ars.next()) {
    	        		types.add(new Type(ars.getInt(1),ars.getString(2)));
        	        }
    	        	poke.setTypes(types);
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    	return poke;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return poke;
	    	} 
    	
    }

    public Pokemon getPokemonById(int id) throws ClassNotFoundException, SQLException{
    	Pokemon poke = new Pokemon();
    	List<Ability> abilities;
    	List<Type> types;
    	CallableStatement stmt = null;
    	CallableStatement astmt = null;
	    ResultSet rs = null;
	    ResultSet ars= null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		stmt = con.prepareCall("{call getPokemonById(?)}");
    	        stmt.setInt(1, id); 
    	        rs = stmt.executeQuery();
    	        while (rs.next() && rs.getString(1) != null) {
    	        	poke = new Pokemon(rs.getString(1), rs.getInt(2), rs.getInt(4),rs.getInt(5));
    	        	poke.setEvolution(getPokemonById(rs.getInt(3)));
    	        	abilities = new ArrayList<Ability>();
    	        	astmt = con.prepareCall("{call getPokemonAbilities(?)}");
    	        	astmt.setInt(1, poke.getId());
    	        	ars = astmt.executeQuery();
    	        	while (ars.next()) {
        	        	abilities.add(new Ability(ars.getInt(1),ars.getString(2)));
        	        }
    	        	ars = null;
    	        	astmt = null;
    	        	poke.setAbilities(abilities);
    	        	types = new ArrayList<Type>();
    	        	astmt = con.prepareCall("{call getPokemontypes(?)}");
    	        	astmt.setInt(1, poke.getId());
    	        	ars = astmt.executeQuery();
    	        	while (ars.next()) {
    	        		types.add(new Type(ars.getInt(1),ars.getString(2)));
        	        }
    	        	poke.setTypes(types);
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    	return poke;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return poke;
	    	} 
    	
    }
    
    public List<Pokemon> getAllPokemons()  throws ClassNotFoundException, SQLException {
    	List<Pokemon> pokemons = new ArrayList<Pokemon>();
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call getAllPokemons}");
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	Pokemon pokeToAdd = new Pokemon(rs.getString(1),rs.getInt(2), rs.getInt(4), rs.getInt(5));
    	        	pokeToAdd.setEvolution(getPokemonById(rs.getInt(3)));
    	        	pokemons.add(pokeToAdd);    	        	
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	        return pokemons;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return pokemons;
	    	} 
    }
    
    public boolean createPokemon(String name, int lvlOnFound) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call createPokemon(?,?)}");
    	        stmt.setString(1, name);
    	        stmt.setInt(2, lvlOnFound);
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
	    return false;
    }

    public boolean deletePokemon(String name)throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		if (getPokemonByName(name).getEvolution() == null) {
	    			//si el pokemon no tiene evolucion eliminamos directamente
	    	        stmt = con.prepareCall("{call deletePokemon(?)}");
	    	        stmt.setString(1, name);
	    	        stmt.executeUpdate();
	    	        return true;
	    		}else {
	    			//si el pokemon tiene evolucion primero le borramos la evolucion
	    			 deleteEvolutionToPokemon(getPokemonByName(name));
	    			 stmt = con.prepareCall("{call deletePokemon(?)}");
		    	        stmt.setString(1, name);
		    	        stmt.executeUpdate();
		    	        return true;
	    		}
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }
    
    public boolean editPokemon(Pokemon pokemon) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call editPokemonBasicData(?,?,?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.setString(2, pokemon.getName());
    	        stmt.setInt(3, pokemon.getOnFound()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
    	
    	return false;
    }
    
    public boolean addAbilityToPokemon(Pokemon pokemon, Ability ability) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call pokemonAddAbility(?,?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.setInt(2, ability.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    public boolean deleteAbilityToPokemon(Pokemon pokemon, Ability ability) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deletePokemonAbility(?,?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.setInt(2, ability.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }
    
    public boolean addTypeToPokemon(Pokemon pokemon, Type type) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call pokemonAddType(?,?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.setInt(2, type.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    public boolean deleteTypeToPokemon(Pokemon pokemon, Type type) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deletePokemonType(?,?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.setInt(2, type.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    public boolean addEvolutionToPokemon(Pokemon pokemon, Pokemon evolution) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call addPokemonEvolution(?,?,?)}");
    	        stmt.setInt(1, pokemon.getId());
    	        stmt.setInt(2, pokemon.getEvoLvl());
    	        stmt.setInt(3, evolution.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    public boolean deleteEvolutionToPokemon(Pokemon pokemon) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deletePokemonEvolution(?)}");
    	        stmt.setInt(1, pokemon.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }

    //methods for users
    public boolean createUser(String name) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call createUser(?)}");
    	        stmt.setString(1, name);
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
	    return false;
    }

    public boolean deleteUser(String name)throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		if (getUserByName(name).getName() != null) {
	    			//si el usuario existe eliminamos
	    	        stmt = con.prepareCall("{call deleteUser(?)}");
	    	        stmt.setString(1, name);
	    	        stmt.executeUpdate();
	    	        return true;
	    		}
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }
    
    public User getUserByName(String name) throws ClassNotFoundException, SQLException{
    	User user= new User();
    	List<Pokemon> pokemons;
    	CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		stmt = con.prepareCall("{call getUserByName(?)}");
    	        stmt.setString(1, name); 
    	        rs = stmt.executeQuery();
    	        while (rs.next() && rs.getString(1) != null) {
    	        	user = new User(rs.getInt(1), rs.getString(2));
    	        	pokemons = getUserPokemons(user);
    	        	user.setOwnPokemons(pokemons);
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    	return user;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return user;
	    	} 
    	
    }
    
    public List<Pokemon> getUserPokemons(User user)  throws ClassNotFoundException, SQLException{
    	List<Pokemon> pokemons = new ArrayList<Pokemon>();
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call userGetAllPokemons(?)}");
    	        stmt.setInt(1, user.getId()); 
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	Pokemon pokeToAdd = new Pokemon(rs.getString(1),rs.getInt(2), rs.getInt(4), rs.getInt(5));
    	        	pokeToAdd.setEvolution(getPokemonById(rs.getInt(3)));
    	        	pokemons.add(pokeToAdd);    	        	
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	        return pokemons;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return pokemons;
	    	} 
    }
    
    public List<User> getAllUsers() throws ClassNotFoundException, SQLException{
    	List<User> users = new ArrayList<User>();
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call getAllUser}");
    	        rs = stmt.executeQuery();
    	        while (rs.next()) {
    	        	User user = new User(rs.getInt(1),rs.getString(2));
    	        	user.setOwnPokemons(getUserPokemons(user));
    	        	users.add(user);    	        	
    	        }
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	        return users;
	    } else {
	    	System.out.println("No se pudo establecer coneccion con la base de datos");
	    	return users;
	    	} 
    }
    
    public boolean addPokemonToUser(User user, Pokemon pokemon)throws ClassNotFoundException, SQLException { 
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call userAddPokemon(?,?)}");
    	        stmt.setInt(1, pokemon.getId());
    	        stmt.setInt(2, user.getId());
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
    	return false;
    }

    public boolean deletePokemonToUser(User user, Pokemon pokemon)throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call userDeletePokemon(?,?)}");
    	        stmt.setInt(1, pokemon.getId());
    	        stmt.setInt(2, user.getId()); 
    	        stmt.executeUpdate();
    	        return true;
	    	}catch(SQLException e){
	    		System.out.println(e);
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    }
	    return false;
    }
    
}
    
    
   