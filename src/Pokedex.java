import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pokedex {
	static List<Type> types = new ArrayList<Type>();
	static List<Ability> abilities = new ArrayList<Ability>();
	static Scanner console = new Scanner(System.in);
    static String consoleString;
    static DBConnection obj;
    
    public static void connection() throws ClassNotFoundException, SQLException {
    	obj = new DBConnection(); 
    }
    
    public static void menu() {
    	System.out.println("");
    	System.out.println("Ingrese una opcion: ");
    	System.out.println("1: Agregar un nuevo tipo.");
    	System.out.println("2: Borrar un tipo.");
    	System.out.println("3: Ver los tipos existentes.");
    	System.out.println("4: Agregar una nueva habilidad.");
    	System.out.println("5: Borrar una habilidad.");
    	System.out.println("6: Ver las habilidad existentes.");
    	System.out.println("7: Buscar pokemon por nombre.");
    	System.out.println("0: Salir.");
    	System.out.println("");
    }
    
    public static List<Type> getTypes() throws ClassNotFoundException, SQLException {
    	List<Type> allTypes = new ArrayList<Type>();
        allTypes = obj.getTypes();
        if( !allTypes.isEmpty()) {
        	System.out.println("Se obtuvieron los tipos");
        	allTypes.forEach((n) -> System.out.println("Tipo: " + n.getName()));
        }  
        System.out.println("");
        return allTypes;
    	
    }
    
	private static List<Ability> getAllAbilities() throws ClassNotFoundException, SQLException {
		List<Ability> allAbilities = new ArrayList<Ability>();
		allAbilities = obj.getAllAbilities();
        if( !allAbilities.isEmpty()) {
        	System.out.println("Se obtuvieron las habilidades");
        	allAbilities.forEach((n) -> System.out.println("Habilidad: " + n.getAbility()));
        }
        System.out.println("");
        return allAbilities;
	}
	
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Bienvenido a Certant Pokedex");
        System.out.println("Intentaremos conectarnos a la base de datos");
        connection();  
        
        if (obj != null) {
        	menu(); // se muestra el menu
            int menuOption = Integer.parseInt(console.nextLine()); // se lee la opcion
            while (menuOption != 0) {
            	switch(menuOption) {
	          	  case 1:
	          		  // Agregar nuevo tipo
	                    System.out.println("Ingrese el nuevo tipo: ");
	                    consoleString = console.nextLine();
	                    Type newType = new Type(0, consoleString);
	                    obj.newType(newType);
	                    break;
	          	  case 2:
	          		  //Borrar un tipo
	                    System.out.println("Ingrese el nombre del tipo a borrar:");
	                    consoleString = console.nextLine();
	                    Type toDeleteType = new Type(0, consoleString);
	                    obj.deleteType(toDeleteType);
	                    break;
	          	  case 3:
	          		  //Listar los tipos
	          		  types = getTypes();
	          		  types.forEach((type) -> {System.out.println("Tipo: " + type.getName());});        		  
	          		  break;
	          	  case 4:
	          		  	// Agregar nueva habilidad
	                    System.out.println("Ingrese el nombre de la nueva habilidad: ");
	                    consoleString = console.nextLine();
	                    Ability newAbility = new Ability(0, consoleString);
	                    obj.newAbility(newAbility);
	          		  break;
	          	  case 5:
	          		  	//Borrar una habilidad
	                    System.out.println("Ingrese el nombre de la habilidad a borrar:");
	                    consoleString = console.nextLine();
	                    Ability toDeleteAbility = new Ability(0, consoleString);
	                    obj.deleteAbility(toDeleteAbility);
	          		  break;
	          	  case 6:
	          		  //Listar las habilidad
	          		  abilities = getAllAbilities();
	          		  types.forEach((type) -> {System.out.println("Habilidad: " + type.getName());});
	          		  break;
	          	  case 7:
	          		  //Buscar un pokemon
	          		  System.out.println("Ingrese el nombre del pokemon que desea buscar.");
	          		  consoleString = console.nextLine();
	          		  Pokemon pokemon = obj.getPokemonByName(consoleString);
	          		  if (pokemon.getName() != null) {
	          			System.out.println("Nombre: " + pokemon.getName());
	          		  }else {
	          			System.out.println("El nombre indicado no corresponde a ningun pokemon.");
	          		  }	          		  
	          		  break;
	          	  default:
	          		  System.out.println("Ingrese una opcion correcta");
            	}
            	menu(); // se muestra el menu
                menuOption = Integer.parseInt(console.nextLine()); // se lee la opcion
        	}
            if (menuOption == 0) {
            	System.out.println("Gracias por usar CertantPokedex");
            	return;
            }
        	
        	
        	
        	
        	
        	
            
            
        }
        
        
        
    }



}
