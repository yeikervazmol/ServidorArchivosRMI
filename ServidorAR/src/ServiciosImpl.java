
public class ServiciosImpl 
	extends	java.rmi.server.UnicastRemoteObject 
	implements Servicios {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Implementations must have an explicit
	//constructor in order to declare the
	// RemoteException exception
	public ServiciosImpl()
	throws java.rmi.RemoteException {
		super();
	}

	public String iniciarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Bienvenido " + nombre + ". Usted esta conectado al servidor.\n";
	}
	public String cerrarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Cerrando sesion.\n";
	}
	
	public String listarArchivosEnServidor(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Listando archivos en el servidor.\n";
	}
	
	public String subirArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Subiendo archivo al servidor.\n";
	}
	
	public String bajarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Bajando archivo desde el servidor.\n";
	}
			
	public String borrarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		return "Borrando archivo en el servidor.\n";
	}

}
