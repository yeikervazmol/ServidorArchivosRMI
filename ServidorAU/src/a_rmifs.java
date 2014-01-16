/**
* @author Karen Troiano		09-10855
* @author Yeiker Vazquez	09-10882
* @grupo  15
*
* Archivo: a_rmifs.java
*
* Descripcion: Contiene el programa principal del
* servidor de autentificacion.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class a_rmifs {
        
        public static Autenticacion a;
        public static String puerto = "";
        public static String archivoUsu = "";

        
        public a_rmifs() {
                try {
                a = new AutenticacionImpl(archivoUsu);
                        
                        LocateRegistry.createRegistry(Integer.parseInt(puerto));
                        
                        Naming.rebind("rmi://localhost:" + puerto+ "/Autenticacion", a);
                }
                catch (Exception e) {
                        System.out.println ("Trouble: " + e);
                }
        }
        
        public static void main(String args[]) {
                
                int argv = args.length;
                // Verificacion de que no se repite algun parametro.
                Boolean[] opciones = { false, false };
                
                if (argv != 4){
                        System.out.println ("Sintaxis de invocacion incorrecta.");
                        System.out.println("\nSintaxis de invocacion: ");
                        System.out.println ("java a_rmifs -f usuarios -p puerto");
                        return;
                } else {
                        for(int j = 0; j < argv; j = j + 2){

                                if(args[j].equals("-p") && !opciones[0]){
                                        puerto = args[j+1];
                                        opciones[0] = true;
                                        
                                } else if(args[j].equals("-f") && !opciones[1]) {
                                	try{
                                		BufferedReader prueba = new BufferedReader(new FileReader(new File(args[j+1])));
                                        archivoUsu = args[j+1];
                                        opciones[1] = true;
                                	} catch (FileNotFoundException e){
                                		System.out.println ("El archivo " + args[j+1] + " no se encuentra en el directorio actual.");
            							System.exit(0);
                                	}
                                } else {
                                        System.out.println ("Sintaxis de invocacion incorrecta.");
                                        System.out.println("\nSintaxis de invocacion: ");
                                        System.out.println ("java a_rmifs -f usuarios -p puerto");
                                        System.exit(0);
                                }
                        }
                }
                
                new a_rmifs();
                
        }
        
        
}