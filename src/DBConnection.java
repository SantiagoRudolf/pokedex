
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;


public class DBConnection {
    public static Connection con = null;
	
	public DBConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    String connectionURL="jdbc:sqlserver://RUDOLF\\SQLEXPRESS:1433;databaseName=pokedex;user=certantuser;password=123";
		try {
			this.con=DriverManager.getConnection(connectionURL);
			
		} catch(SQLException e){

   	     System.out.println(e);

   	    }
	}
	public static Connection getCon() {
		return con;
	}
	public static void main(String args[ ]){}
    
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
    
    public void newType(Type t) throws ClassNotFoundException, SQLException {
    
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call insertType(?)}"); // se prepara para llamar al SP insertType
    	        stmt.setString(1, t.getName()); // se agrega la variable nombre del nuevo tipo 
    	        stmt.executeUpdate();
    	        System.out.println("Se agrego el nuevo tipo");
	    	}catch(SQLException e){
	    		System.out.println("No fue posible agregar el tipo, verifique que este no exita");
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
	}
    
    public void deleteType(Type t) throws ClassNotFoundException, SQLException {
        
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deleteType(?)}"); // se prepara para llamar al SP insertType
    	        stmt.setString(1, t.getName()); // se agrega la variable nombre del nuevo tipo 
    	        stmt.executeUpdate();
    	        System.out.println("Se borro el tipo");
	    	}catch(SQLException e){
	    		System.out.println("No fue posible borrar el tipo, verifique que este existe o el nombre es correcto");
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
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

    public void newAbility(Ability a) throws ClassNotFoundException, SQLException {
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call insertAbility(?)}");
    	        stmt.setString(1, a.getAbility()); 
    	        stmt.executeUpdate();
    	        System.out.println("Se agrego la nueva habilidad");
	    	}catch(SQLException e){
	    		System.out.println("No fue posible agregar la habilidad, verifique que este no exita");
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
    }

    public void deleteAbility(Ability a) throws ClassNotFoundException, SQLException {
        
    	CallableStatement stmt = null;
	    Connection con = getCon();
	    
	    if (con != null){
	    	try{
    	        stmt = con.prepareCall("{call deleteAbility(?)}");
    	        stmt.setString(1, a.getAbility());
    	        stmt.executeUpdate();
    	        System.out.println("Se borro la habilidad");
	    	}catch(SQLException e){
	    		System.out.println("No fue posible borrar la habilidad, verifique que este existe o el nombre es correcto");
	    	}
	    } else {
	    	System.out.println("No hay conexcion con la base de datos, verifique esta iniciada y que existe");
	    	}
	}

    //methos for pokemons
    public Pokemon getPokemonByName(String name) throws ClassNotFoundException, SQLException{
    	Pokemon poke = new Pokemon();
    	CallableStatement stmt = null;
	    ResultSet rs = null;
	    Connection con = getCon();	    
	    if (con != null){
	    	try{
	    		stmt = con.prepareCall("{call getPokemonByName(?)}");
    	        stmt.setString(1, name); 
    	        rs = stmt.executeQuery();
    	        while (rs.next() && rs.getString(1) != null) {
    	        	poke = new Pokemon(rs.getString(1), rs.getInt(2),rs.getInt(3), rs.getInt(4),rs.getInt(5), rs.getInt(6), rs.getInt(7));
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
    
    
    
    
    
}