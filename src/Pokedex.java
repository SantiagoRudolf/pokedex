import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pokedex {
	static List<Pokemon> pokemons = new ArrayList<Pokemon>();
	static List<Type> types = new ArrayList<Type>();
	static List<Ability> abilities = new ArrayList<Ability>();
	static Scanner console = new Scanner(System.in);
    static String consoleString;
    static DBConnection obj;
    static boolean resp;
    
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
    	System.out.println("7: Ver pokemon por nombre.");
    	System.out.println("8: Editar informacion de un pokemon.");
    	System.out.println("9: Crear pokemon.");
    	System.out.println("10: Borrar pokemon.");
    	System.out.println("11: Ver todos los pokemons.");
    	System.out.println("0: Salir.");
    	System.out.println("");
    }
    
    public static void menuPokeEdit(Pokemon pokemon) {
    	System.out.println("Usted va a editar el pokemon: " + pokemon.name);
    	System.out.println("");
    	System.out.println("Ingrese una opcion: ");
    	System.out.println("1: Modificar nombre.");
    	System.out.println("2: Modificar lvl al que se encuentra.");
    	System.out.println("3: Agregar una nueva habilidad.");
    	System.out.println("4: Borrar una habilidad.");
    	System.out.println("5: Agregar un tipo.");
    	System.out.println("6: Borrar un tipo.");
    	System.out.println("7: Agregar evolucion.");
    	System.out.println("8: Borrar evolucion.");
    	System.out.println("0: Cuando termine de modificar los datos deseados.");
    	System.out.println("");
    }
    
    public static List<Type> getTypes() throws ClassNotFoundException, SQLException {
    	List<Type> allTypes = new ArrayList<Type>();
        allTypes = obj.getTypes();
        return allTypes;    	
    }
    
	private static List<Ability> getAllAbilities() throws ClassNotFoundException, SQLException {
		List<Ability> allAbilities = new ArrayList<Ability>();
		allAbilities = obj.getAllAbilities();
        return allAbilities;
	}
	
	public static void editPokemon(Pokemon poke) throws ClassNotFoundException, SQLException{
    	menuPokeEdit(poke);
    	int menuEditOption = Integer.parseInt(console.nextLine()); // se lee la opcion
    	while (menuEditOption != 0) {
    		switch(menuEditOption) {
        	  case 1:
        		  //Modificar nombre
        		  String newName;
        		  System.out.println("Ingrese nuevo nombre del pokemon");
        		  newName = console.nextLine();
        		  poke.setName(newName);
        		  if (obj.editPokemon(poke)) {
        			  System.out.println("Se actualizaron los datos del pokemon");
        		  }
                  break;
        	  case 2:
        		  //Modificar lvl al que se encuentra
        		  int newLvlonFoun;
        		  System.out.println("Ingrese lvl al que se encuentra el pokemon");
        		  newLvlonFoun = Integer.parseInt(console.nextLine());
        		  poke.setOnFound(newLvlonFoun);
        		  if (obj.editPokemon(poke)) {
        			  System.out.println("Se actualizaron los datos del pokemon");
        		  }
                  break;
        	  case 3:
        		  //Agregar una nueva habilidad
        		  String newAbilityName = "";
        		  Ability newAbility= new Ability();
        		  System.out.println("Ingrese nombre de la habilidad que desea agregar al pokemon");
        		  newAbilityName = console.nextLine();
        		  //buscamos si la habilidad existe
        		  newAbility = obj.getAbilityByName(newAbilityName);
        		  
        		  //se agrego esta iteracion porque el metodo contains() no funcionaba
        		  //para verificar si el pokemon no posee la habilidad
        		  boolean tieneHabilidad = true;
        		  int i = 0;
        		  while (i < poke.getAbilities().size()) {
        			  if (poke.getAbilities().get(i).getAbility().replaceAll("\\s","").equalsIgnoreCase(newAbility.getAbility().replaceAll("\\s",""))) {
        				  tieneHabilidad= false;
        			  }
        			  i++;
        		  }
        		  
        		  //si la habilidad existe y el pokemon no la posee la agregamos
        		  if (newAbility.getAbility() != null && tieneHabilidad) {
        			  //Agregamos 
        			  if (obj.addAbilityToPokemon(poke, newAbility)) {
        				  System.out.println("Se agrego la habilidad");
        			  }
        		  }else {
    				  System.out.println("No se pudo agregar la habilidad, verifique la habilidad exista o el pokemon no la posea");
    			  }
        		  break;
        	  case 4:
        		  //Borrar una habilidad
        		  String toDeleteName = "";
        		  Ability toDeleteAbility= new Ability();
        		  System.out.println("Ingrese nombre de la habilidad a borrar del pokemon");
        		  toDeleteName = console.nextLine();
        		  //buscamos si la habilidad existe
        		  toDeleteAbility = obj.getAbilityByName(toDeleteName);
        		  
        		  //se agrego esta iteracion porque el metodo contains() no funcionaba
        		  //para verificar si el pokemon posee la habilidad
        		  boolean noHabilidad = false;
        		  int x = 0;
        		  while (x < poke.getAbilities().size()) {
        			  if (poke.getAbilities().get(x).getAbility().replaceAll("\\s","").equalsIgnoreCase(toDeleteAbility.getAbility().replaceAll("\\s",""))) {
        				  noHabilidad= true;
        			  }
        			  x++;
        		  }
        		  
        		  //si la habilidad existe y el pokemon la posee la borramos
        		  if (toDeleteAbility.getAbility() != null && noHabilidad) {
        			  //Agregamos 
        			  if (obj.deleteAbilityToPokemon(poke, toDeleteAbility)) {
        				  System.out.println("Se borro la habilidad");
        			  }
        		  }else {
    				  System.out.println("No se pudo borrar la habilidad, verifique la habilidad exista o el pokemon la posea");
    			  }
        		  break;
        	  case 5:
        		  //Agregar un tipo
        		  String newTypeName = "";
        		  Type newType= new Type();
        		  System.out.println("Ingrese nombre del tipo que desea agregar al pokemon");
        		  newTypeName = console.nextLine();
        		  //buscamos el tipo existe
        		  newType = obj.getTypeByName(newTypeName);
        		  
        		  //se agrego esta iteracion porque el metodo contains() no funcionaba
        		  //para verificar si el pokemon no poseia el tipo
        		  boolean tieneTipo = true;
        		  int y = 0;
        		  while (y < poke.getTypes().size()) {
        			  if (poke.getTypes().get(y).getName().equals(newType.getName())) {
        				  tieneTipo= false;
        			  }
        			  y++;
        		  }
        		  
        		  //si el tipo existe y el pokemon no lo posee lo agregamos
        		  if (newType.getName() != null && tieneTipo) {
        			  //Agregamos 
        			  if (obj.addTypeToPokemon(poke, newType)) {
        				  System.out.println("Se agrego el tipo");
        			  }
        		  }else {
    				  System.out.println("No se pudo agregar el tipo, verifique que exista o el pokemon no lo posea");
    			  }
        		  break;
        	  case 6:
        		  //Borrar un tipo
        		  String deleteTypeName = "";
        		  Type toDeleteType= new Type();
        		  System.out.println("Ingrese nombre del tipo que desea borrar del pokemon");
        		  deleteTypeName = console.nextLine();
        		  if (deleteTypeName.equals("")) {
        			  System.out.println("Debe ingresar un tipo");
        		  }else {
        		  //buscamos si la el tipo existe
        		  toDeleteType = obj.getTypeByName(deleteTypeName);
        		  
        		  //se agrego esta iteracion porque el metodo contains() no funcionaba
        		  //para verificar si el pokemon poseia el tipo
        		  boolean noTipo = false;
        		  int z = 0;
        		  while (z < poke.getTypes().size()) {
        			  if (poke.getTypes().get(z).getName().equals(toDeleteType.getName())) {
        				  noTipo= true;
        			  }
        			  z++;
        		  }
        		  
        		  
        		  //si el tipo existe y el pokemon lo posee lo borramos
        		  if (toDeleteType.getName() != null && noTipo) {
        			  //Agregamos 
        			  if (obj.deleteTypeToPokemon(poke, toDeleteType)) {
        				  System.out.println("Se borro el tipo");
        			  }
        		  }else {
    				  System.out.println("No se pudo borrar el tipo, verifique que exista o el pokemon lo posea");
    			  }
        		  }
        		  break;
        	  case 7:
        		  //Agregar evolucion
        		  String evolutionName = "";
        		  Pokemon evolution = new Pokemon();
        		  System.out.println("Ingrese nombre de la evolucion que desea agregar al pokemon");
        		  evolutionName = console.nextLine();
        		  //buscamos la evolucion
        		  evolution = obj.getPokemonByName(evolutionName);
        		  //si la evolucion existe y el pokemon posee evolucion agregamos
        		  if (evolution.getName() != null && poke.getEvolution().getName() == null) {
        			  //Establecemos el lvl al que 
        			  String evolutionLvl = "";
            		  System.out.println("Ingrese el nivel al que evoluciona");
            		  evolutionLvl = console.nextLine();
            		  poke.setEvoLvl(Integer.parseInt(evolutionLvl));
        			  //Agregamos 
        			  if (obj.addEvolutionToPokemon(poke, evolution)) {
        				  System.out.println("Se agrego la evolucion");
        			  }
        		  }else {
    				  System.out.println("No se pudo agregar la evolucion, verifique que exista o el pokemon no posea una evolucion");
    			  }
        		  break;
        	  case 8:
        		  //Borrar evolucion
        		  if (poke.getEvolution() != null) {
        			  //Agregamos
        			  String resp = "N";
        			  System.out.println("ESTA SEGURO DE BORRAR LA EVOLUCION? S/N");
            		  resp = console.nextLine();
            		  if(resp.equalsIgnoreCase("S")) {
	        			  if (obj.deleteEvolutionToPokemon(poke)) {
	        				  System.out.println("Se borro la evolucion del pokemon");
	        			  }            			  
            		  } else {
            			  System.out.println("NO SE BORRARA LA EVOLUCION");
            		  }
        		  }else {
    				  System.out.println("No se pudo borrar la evolucion, verifique que el pokemon posea una evolucion");
    			  }
        		  break;
        	  default:
        		  System.out.println("Ingrese una opcion correcta");
    		}
    		//Actualizamos los datos del pokemon con el que estamos trabajando
    		poke = obj.getPokemonByName(poke.getName());
    		menuPokeEdit(poke); // se muestra el menu
    		menuEditOption = Integer.parseInt(console.nextLine()); // se lee la opcion
    	}
    	if (menuEditOption == 0) {
    		System.out.println("Cerrando editor de pokemon");
    	}
    }
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Bienvenido a Certant Pokedex");
        System.out.println("Intentaremos conectarnos a la base de datos");
        connection();  
        
        if (obj != null) {
        	menu(); // se muestra el menu
            int menuOption = Integer.parseInt(console.nextLine()); // se lee la opcion
            while (menuOption != 0) {
            	resp = false;
            	switch(menuOption) {
	          	  case 1:
	          		  // Agregar nuevo tipo
	                  System.out.println("Ingrese el nuevo tipo: ");
	                  consoleString = console.nextLine();
	                  Type newType = new Type(0, consoleString);	                  
	                  
	                  //se agrego esta iteracion porque el metodo contains() no funcionaba
	          		  //para verificar si tipo existe en la BD
	          		  boolean existeTipo = false;
	          		  List<Type> actualTypes = new ArrayList<Type>();
	          		  actualTypes = obj.getTypes();
	          		  int i = 0;
	          		  while (i < actualTypes.size() && !existeTipo) {
	          			  if (actualTypes.get(i).getName().replaceAll("\\s","").equalsIgnoreCase(consoleString)) {
	          				  existeTipo = true;
	          			  }
	          			  i++;
	          		  }
	          		  
	          		  if (!existeTipo) {
	                    if(obj.newType(newType)) {
	                    	System.out.println("Se agrego el nuevo tipo");
	                    }
	                  }else {
	                	  System.out.println("No se pudo agrego el nuevo tipo, verifique que este no exista");
	                  }
	                  break;
	          	  case 2:
	          		  //Borrar un tipo
	                    System.out.println("Ingrese el nombre del tipo a borrar:");
	                    consoleString = console.nextLine();
	                    Type toDeleteType = new Type(0, consoleString);	                    
	                    
	                    //se agrego esta iteracion porque el metodo contains() no funcionaba
		          		//para verificar si tipo existe en la BD
	                    List<Type> actualTypes2 = new ArrayList<Type>();
	                    actualTypes2 = obj.getTypes();
		          		boolean existeTipo2 = false;
		          		int x = 0;
		          		while (x < actualTypes2.size()) {
		          			  if (actualTypes2.get(x).getName().replaceAll("\\s","").equalsIgnoreCase(consoleString)) {
		          				  existeTipo2 = true;
		          			  }
		          			  x++;
		          		}
		          		
		          		if(existeTipo2) {
		                    if (obj.deleteType(toDeleteType)) {
		                    	System.out.println("Se borro el tipo");
		                    }
		                }else {
		                	System.out.println("No se pudo borrar el nuevo tipo, verifique que este exista");
		                }
	                    break;
	          	  case 3:
	          		  //Listar los tipos
	          		  types = getTypes(); 
	                  if( !types.isEmpty()) {
	                  	System.out.println("Se obtuvieron los tipos");
	                  	types.forEach((n) -> System.out.println("Tipo: " + n.getName()));
	                  }  
	                  System.out.println("");
	          		  break;
	          	  case 4:
	          		  	// Agregar nueva habilidad
	                    System.out.println("Ingrese el nombre de la nueva habilidad: ");
	                    consoleString = console.nextLine();
	                    Ability newAbility = new Ability(0, consoleString);
	                    
	                    //se agrego esta iteracion porque el metodo contains() no funcionaba
	                    //para verificar si la habilidad no existe en la BD
	                    List<Ability> actualAbilities = new ArrayList<Ability>();
	                    actualAbilities = obj.getAllAbilities();
		          		boolean existeHabilidad = false;
		          		int y = 0;
		          		while (y < actualAbilities.size()) {
		          			if (actualAbilities.get(y).getAbility().replaceAll("\\s","").equalsIgnoreCase(consoleString)) {
		          				existeHabilidad = true;
		          			}
		          			y++;
		          		}
	                    if(!existeHabilidad) {
		                    if (obj.newAbility(newAbility)) {
		                    	System.out.println("Se agrego la nueva habilidad");
		                    }
	                    } else {
	                    	System.out.println("No se pudo agrego la nueva habilidad, verifique que esta no exista");
	                    }
	          		  break;
	          	  case 5:
	          		  	//Borrar una habilidad
	                    System.out.println("Ingrese el nombre de la habilidad a borrar:");
	                    consoleString = console.nextLine();
	                    Ability toDeleteAbility = new Ability(0, consoleString);
	                    
	                    //se agrego esta iteracion porque el metodo contains() no funcionaba
	                    //para verificar si la habilidad existe en la BD
	                    List<Ability> actualAbilities2 = new ArrayList<Ability>();
	                    actualAbilities2 = obj.getAllAbilities();
		          		boolean noExisteHabilidad = false;
		          		int z = 0;
		          		while (z < actualAbilities2.size()) {
		          			if (actualAbilities2.get(z).getAbility().replaceAll("\\s","").equalsIgnoreCase(consoleString)) {
		          				noExisteHabilidad = true;
		          			}
		          			z++;
		          		}
		          		if (noExisteHabilidad) {
		          			if (obj.deleteAbility(toDeleteAbility)) {
		            	        System.out.println("Se borro la habilidad");
		                    }
		          		}else {
		          			System.out.println("No se pudo borrar la habilidad, verifique que esta exista");
		          		}
	                    
	                    
	          		  break;
	          	  case 6:
	          		  //Listar las habilidad
	          		  abilities = getAllAbilities();
	                  if( !abilities.isEmpty()) {
	                  	System.out.println("Se obtuvieron las habilidades");
	                  	abilities.forEach((b) -> System.out.println("Habilidad: " + b.getAbility()));
	                  }
	                  System.out.println("");
	          		  break;
	          	  case 7:
	          		  //Buscar un pokemon
	          		  System.out.println("Ingrese el nombre del pokemon que desea buscar.");
	          		  consoleString = console.nextLine();
	          		  Pokemon pokemon = obj.getPokemonByName(consoleString);
	          		  if (pokemon.getName() != null) {
	          			  System.out.println("Nombre: " + pokemon.getName());
	          			  System.out.println("Se encuenta al lvl: " + pokemon.getOnFound());
	          			  List<Ability> abilities = new ArrayList<Ability>();
	          			  abilities = pokemon.getAbilities();
	          			  if (!abilities.isEmpty()) {
	          				  System.out.println("Sus habilidades son: ");
	          				  abilities.forEach((ability) -> {System.out.println("Habilidad: " + ability.getAbility());});
	          			  } else {
	          				  System.out.println("Este pokemon no posee habilidades");
	          			  }
	          			  List<Type> types = new ArrayList<Type>();
	          			  types = pokemon.getTypes();
	          			  if (!types.isEmpty()) {
	          				  System.out.println("Sus tipos son: ");
	          				  types.forEach((type) -> {System.out.println("Tipo: " + type.getName());});
	          			  } else {
	          				  System.out.println("Este pokemon no es de ningun tipo");
	          			  }
	          			  if(pokemon.getEvolution().getName() != null){
	          				System.out.println("Su evolucion es: " + pokemon.getEvolution().getName());
	          				System.out.println("Evoluciona al lvl: " + pokemon.getEvoLvl());
	          			  } else {
	          				System.out.println("Este pokemon no posee evolucion");
	          			  }
	          		  }else {
	          			System.out.println("El nombre indicado no corresponde a ningun pokemon.");
	          		  }	          		  
	          		  break;
	          	  case 8:
	          		  //Editar un pokemon
	          		  System.out.println("Ingrese el nombre del pokemon que desea editar.");
	          		  consoleString = console.nextLine();
	          		  Pokemon pokemonToEdit = obj.getPokemonByName(consoleString);
	          		  if (pokemonToEdit.getName() != null) {
	          			  editPokemon(pokemonToEdit);
	          		  }else {
	          			System.out.println("El nombre indicado no corresponde a ningun pokemon.");
	          		  }
	          		  break;
	          	  case 9:
	          		  //Crear pokemon.
	          		  String newPokeName;
	          		  int newPokeLvlOnFound;
	          		  System.out.println("Ingrese el nombre del pokemon que desea crear.");
	          		  newPokeName = console.nextLine();
	          		  if (obj.getPokemonByName(newPokeName).getName() == null) {
	          			  //Si no existe un pokemon con ese nombre lo vamos a crear
	          			  System.out.println("Ingrese el nivel al que se encuentra el pokemon.");
	          			  newPokeLvlOnFound = Integer.parseInt(console.nextLine());
	          			  if (obj.createPokemon(newPokeName, newPokeLvlOnFound)) {
	          				System.out.println("Se creo exitosamente el pokemon"); 
	          			  }
	          		  } else {
	          			System.out.println("Ya existe un pokemon con ese nombre");
	          		  }
	          		  break;
	          	  case 10:
	          		  //Borrar un pokemon
	          		  String toDeletePokeName;
	          		  System.out.println("Ingrese el nombre del pokemon que desea borrar.");
	          		  System.out.println("Si el pokemon posee evolucion primero se borrara la ligadura a la evolucion y luego se borrara el pokemon.");
	          		  toDeletePokeName = console.nextLine();
          			  Pokemon deletePoke = obj.getPokemonByName(toDeletePokeName);
	          		  if (deletePoke != null) {
		          		  String resp = "N";
		          		  System.out.println("ESTA SEGURO DE BORRAR EL POKEMON? S/N");
		          		  resp = console.nextLine();
		          		  if(resp.equalsIgnoreCase("S")) {
		        			  if (obj.deletePokemon(deletePoke.getName())) {
		        				  System.out.println("Se borro el pokemon");
		        			  }            			  
		          		  } else {
		          			  System.out.println("NO SE BORRARA EL POKEMON");
		          		  } 
	          		  }else {
	          			System.out.println("No existe un pokemon con ese nombre");
	          		  }
	          		  
	          		  break;
	          	  case 11:
	          		  //Ver todos los pokemones
	          		  pokemons = obj.getAllPokemons();
	          		  if(!pokemons.isEmpty()) {
	          			System.out.println("Los pokemons en la BD son:");
	          			pokemons.forEach((a) -> {
	          				System.out.println("");
	          				 System.out.println("Nombre: " + a.getName());
		          			  System.out.println("Se encuenta al lvl: " + a.getOnFound());
		          			  List<Ability> aAbilities = new ArrayList<Ability>();
		          			  aAbilities = a.getAbilities();
		          			  if (!aAbilities.isEmpty()) {
		          				  System.out.println("Sus habilidades son: ");
		          				  aAbilities.forEach((ability) -> {System.out.println("Habilidad: " + ability.getAbility());});
		          			  } else {
		          				  System.out.println("Este pokemon no posee habilidades");
		          			  }
		          			  List<Type> aTypes = new ArrayList<Type>();
		          			  aTypes = a.getTypes();
		          			  if (!aTypes.isEmpty()) {
		          				  System.out.println("Sus tipos son: ");
		          				  aTypes.forEach((type) -> {System.out.println("Tipo: " + type.getName());});
		          			  } else {
		          				  System.out.println("Este pokemon no es de ningun tipo");
		          			  }
		          			  if(a.getEvolution().getName() != null){
		          				System.out.println("Su evolucion es: " + a.getEvolution().getName());
		          				System.out.println("Evoluciona al lvl: " + a.getEvoLvl());
		          				List<Type> pokeEvoTypes = new ArrayList<Type>();
		          				try {
									pokeEvoTypes = obj.getPokemonByName(a.getEvolution().getName()).getTypes();
								} catch (ClassNotFoundException | SQLException e) {
									e.printStackTrace();
								};
		          				if (pokeEvoTypes != null) {
		          					pokeEvoTypes.forEach((type) -> {System.out.println("Tipo: " + type.getName());});
		          				}else {
		          					System.out.println("La evolucion no posee tipo");
		          				}
		          			  } else {
			          				System.out.println("Este pokemon no posee evolucion");
		          			  }
	          			});
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
