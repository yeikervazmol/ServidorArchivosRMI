/**
* @author Karen Troiano		09-10855
* @author Yeiker Vazquez	09-10882
* @grupo  15
*
* Archivo: s_rmifs.java
*
* Descripcion: Contiene el programa principal del
* servidor de archivos.
*/

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class s_rmifs {
	
	public static String host = "";
	public static String puertolocal = "";
	public static String puerto = "";
	public static String comando;
	// Interfaces para la conexion 
	public static Servicios c;

	
	public s_rmifs() {
		try {
			c = new ServiciosImpl(host, puerto);
			
			LocateRegistry.createRegistry(Integer.parseInt(puertolocal));
			
			Naming.rebind("rmi://localhost:" + puertolocal + "/Servicios", c);
			
		}
		catch (Exception e) {
			System.out.println ("Fallas conectandose al servidor de autenticacion.");
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		
		int argv = args.length;
		// Verificacion de que no se repite algun parametro.
		Boolean[] opciones = { false, false, false };
		
		if (argv != 6){
			System.out.println ("Sintaxis de invocacion incorrecta.");
			System.out.println("\nSintaxis de invocacion: ");
			System.out.println ("java s_rmifs -l puertolocal -h host -r puerto");
			return;
		} else {
			for(int j = 0; j < argv; j = j + 2){

				if(args[j].equals("-l") && !opciones[0]){
					puertolocal = args[j+1];
					opciones[0] = true;
					
				}else if(args[j].equals("-h") && !opciones[1]){
					host = args[j+1];
					opciones[1] = true;
					
				}else if(args[j].equals("-r") && !opciones[2]){
					puerto = args[j+1];
					opciones[2] = true;
					
				} else {
					System.out.println ("Sintaxis de invocacion incorrecta.");
					System.out.println("\nSintaxis de invocacion: ");
					System.out.println ("java s_rmifs -l puertolocal -h host -r puerto");
					System.exit(0);
				}
			}
		}
		
		new s_rmifs();
		
		try {
			c.inicializarLog();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		while (true) {
			comando = System.console().readLine();
			if (comando.equals("log")) {	
				try {
					System.out.println("Log de los ultimos 20 comandos:\n");
					System.out.println(c.entregarLog("09108550910882"));
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (comando.equals("sal")) {
				System.out.println ( "Hasta luego." );
				System.exit(0);
			} else {
				System.out.println ( "Opcion invalida. Intente de nuevo.\n" );
				System.out.println 
				( 	"Comandos disponibles:\n" +
					"log \tMuestra una lista de los ultimos 20 comandos que los clientes\n " +
						"\t han tenviado al servidor, identificando de que cliente\n\t provienen.\n" +
					"sal\tTermina la ejecución del programa servidor.\n" +
					"\n" );
			}
			
		}
		
	}
	
	
}