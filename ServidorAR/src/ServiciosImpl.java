
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

	public String listarArchivosEnServidor()
	throws java.rmi.RemoteException {
		return "Listando archivos en el servidor.\n";
	}
	
	public String subirArchivo()
	throws java.rmi.RemoteException {
		return "Subiendo archivo al servidor.\n";
	}
	
	public String bajarArchivo()
	throws java.rmi.RemoteException {
		return "Bajando archivo desde el servidor.\n";
	}
			
	public String borrarArchivo()
	throws java.rmi.RemoteException {
		return "Borrando archivo en el servidor.\n";
	}

}
