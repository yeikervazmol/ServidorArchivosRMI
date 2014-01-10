import java.rmi.Naming;
import java.rmi.RemoteException;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;



public class c_rmifs {
	
	public static Servicios s;
	public static Boolean salir;
	public static String nombre;
	public static String clave;
	public static Boolean hayArchivoCom = false;
	public static Boolean hayArchivoUsu = false;
	public static String archivoCom = "";
	public static String archivoUsu = "";
	
	public static void main(String[] args) {
		try {
			
			String servidor = "";
			String puerto = "";
			String [] datosUsuario;
			// Verificacion de que no se repite algun parametro.
			Boolean[] opciones = { false, false, false, false };
			
			
			int argv = args.length;
			String comando;
			salir = false;
			BufferedReader comandos = null;
			BufferedReader usuarios = null;
			
			
			if (argv % 2 != 0){
				System.out.println ("Sintaxis de invocacion incorrecta.");
				return;
			} else {
				for(int j = 0; j < argv; j = j + 2){

					if(args[j].equals("-f") && !opciones[0]){
						hayArchivoUsu = true;
						archivoUsu = args[j+1];
						usuarios = new BufferedReader(new FileReader(new File(archivoUsu)));
						opciones[0] = true;
						
					}else if(args[j].equals("-p") && !opciones[1]){
						puerto = args[j+1];
						opciones[1] = true;
						
					}else if(args[j].equals("-m") && !opciones[2]){
						servidor = args[j+1];
						opciones[2] = true;
						
					}else if(args[j].equals("-c") && !opciones[3]){						
						hayArchivoCom = true;
						archivoCom = args[j+1];
						comandos = new BufferedReader(new FileReader(new File(archivoCom)));
						opciones[3] = true;
						
					} else {
						System.out.println ("Sintaxis de invocacion incorrecta.");
						return;
					}
				}
			}
			
			s = (Servicios)
					Naming.lookup("rmi://" + servidor + ":" + puerto + "/Servicios");
			
			if(hayArchivoUsu){
				datosUsuario = (usuarios.readLine()).split(":");
				nombre = datosUsuario[0];
				clave = datosUsuario[1];
			}else{
				System.out.print ("Introduzca su nombre de usuario: ");
				nombre = System.console().readLine();
				
				clave = new String(System.console().readPassword("\nIntroduzca su clave: "));
			}

			System.out.println (s.iniciarSesion(nombre,clave));
			
			if (hayArchivoCom){
				while( ((comando = comandos.readLine()) != null) && !salir ){
					ejecutarComando(comando);
				}
				comandos.close();
			}
			
			while(!salir){
				comando = System.console().readLine();
				ejecutarComando(comando);
			}
		
		} 
		
		catch (MalformedURLException murle) {
			System.out.println ();
			System.out.println (
			"MalformedURLException");
			System.out.println ( murle ); 
		}

		catch (NotBoundException nbe) {
			System.out.println ();
			System.out.println ("NotBoundException");
			System.out.println (nbe);
		}
		
		catch (FileNotFoundException e) {
			System.out.println ();
			System.out.println ("FileNotFoundException");
			System.out.println (e);
		} 
		
		catch (IOException e) {
			System.out.println ();
			System.out.println ("IOException");
			System.out.println (e);
		}
	}
	
	public static void mostrarArchivosLocales(){
		String path = "."; 
		
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
 
		for (int i = 0; i < listOfFiles.length; i++){
 
			if (	listOfFiles[i].isFile()){
				files = listOfFiles[i].getName();
				if	( !(files.equals("Servicios.java")
						|| files.equals("Servicios.class")
						|| files.equals("c_rmifs.java")
						|| files.equals("c_rmifs.class")
						)
					){
					
					
					if(hayArchivoUsu && hayArchivoCom ){
						if(!(files.equals(archivoUsu) || files.equals(archivoCom)) ){
							System.out.println("\t" + files);
						}
						
					} else if(!hayArchivoUsu && hayArchivoCom ){
						if(!files.equals(archivoCom) ){
							System.out.println("\t" + files);
						}
						
					} else if(hayArchivoUsu && !hayArchivoCom ){
						if(!files.equals(archivoUsu)){
							System.out.println("\t" + files);
						}
					} else if(!hayArchivoUsu && !hayArchivoCom ){
						System.out.println("\t" + files);
					}				
					
				}
			}
		}
	}

	public static void ejecutarComando(String comando){
		try{
			switch (comando){
				case "rls":
					System.out.println ( s.listarArchivosEnServidor(nombre, clave));
					break;
				case "lls":
					System.out.println ( "\nArchivos locales:\n" );
					mostrarArchivosLocales();
					break;
				case "sub archivo":
					System.out.println ( s.subirArchivo(nombre, clave));
					break;
				case "baj archivo":
					System.out.println ( s.bajarArchivo(nombre, clave));
					break;
				case "bor archivo":
					System.out.println ( s.borrarArchivo(nombre, clave));
					break;
				case "info":
					System.out.println 
					( 	"Comandos disponibles:\n" +
						"rls\t\tMuestra la lista de archivos disponibles en servidor " +
							"\n\t\tcentralizado.\n" +
						"lls\t\tMuestra la lista de archivos disponibles localmente.\n" +
						"sub archivo\tSube un archivo al servidor remoto " +
							"(Ej: sub clase.pdf). El\n\t\tarchivo especificado como " +
							"parámetro debe estar en la lista\n\t\tde archivos " +
							"disponibles para el cliente localmente.\n" +
						"baj archivo\tBaja un archivo desde el servidor remoto" +
							"\n\t\t(Ej: baj ejemplo.c). El archivo especificado debe " +
							"estar en\n\t\tla lista de archivos disponibles en el servidor " +
							"\n\t\tcentralizado para que el comando funcione " +
							"adecuadamente.\n" +
						"bor archivo\tBorra el archivo en el servidor remoto.\n" +
						"info\t\tMuestra la lista de comandos que el cliente puede " +
							"usar con\n\t\tuna breve descripción de cada uno de ellos.\n" +
						"sal\t\tTermina la ejecución del programa cliente.\n" +
						"\n" );
					break;
				case "sal":
					System.out.println (s.cerrarSesion(nombre, clave));
					System.out.println ( "Hasta luego.\n" );
					salir = true;
					break;
				
			}
		}

		catch (RemoteException re) {
			System.out.println ();
			System.out.println ( "RemoteException");
			System.out.println (re); 
		}
	}
}