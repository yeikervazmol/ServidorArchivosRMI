import java.rmi.Naming;
import java.rmi.RemoteException;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class c_rmifs {
	
	public static Servicios s;
	public static Boolean salir;
	
	public static void main(String[] args) {
		try {
			
			s = (Servicios)
				Naming.lookup("rmi://localhost:2000/Servicios");
			String archivoE = "";
			Boolean hayArchivo = false;
			String comando;
			salir = false;
			BufferedReader comandos = null;
			
			switch (args.length) {
				case 1:
					hayArchivo = true;
					archivoE = args[0];
					comandos = new BufferedReader(new FileReader(new File(archivoE)));
					break;
			
			}
			System.out.println ("\nBienvenido\n");
			if (hayArchivo){
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

	public static void ejecutarComando(String comando){
		try{
			switch (comando){
				case "rls":
					System.out.println ( s.listarArchivosEnServidor());
					break;
				case "lls":
					System.out.println ( "Listando archivos locales.\n" );
					break;
				case "sub archivo":
					System.out.println ( s.subirArchivo());
					break;
				case "baj archivo":
					System.out.println ( s.bajarArchivo());
					break;
				case "bor archivo":
					System.out.println ( s.borrarArchivo());
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