import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AutenticacionImpl 
        extends        java.rmi.server.UnicastRemoteObject 
        implements Autenticacion {
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public static String archivoUsu = "";
        
        // Implementations must have an explicit
        //constructor in order to declare the
        // RemoteException exception
        public AutenticacionImpl(String archivo)
        throws java.rmi.RemoteException {
                super();
                archivoUsu = archivo;
        }

        
        public Boolean autenticarUsuario(String nombre, String clave)
        throws java.rmi.RemoteException {
                BufferedReader usuarios = null;
                String[] datosUsuario = { "" , ""};
                String linea = "";
                String nombreLinea = "";
                String claveLinea = "";
                
                try {
                        usuarios = new BufferedReader(new FileReader(new File(archivoUsu)));
                        linea = usuarios.readLine();
                        while ( linea != null) {
                                datosUsuario = linea.split(":");
                                nombreLinea = datosUsuario[0];
                                claveLinea = datosUsuario[1];
                                if (nombre.equals(nombreLinea) && clave.equals(claveLinea)) {
                                        return true;
                                }
                                linea = usuarios.readLine();
                        }
                        
                } catch (FileNotFoundException e) {
                        System.out.println("Archivo no encontrado.");
                        System.exit(0);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return false;
        }

}