
public class ServiciosImpl 
	extends	java.rmi.server.UnicastRemoteObject 
	implements Servicios {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String claveServidor = "09108550910882"; 
	public static String[] logs = new String[20];
	private static int indice;
	
	// Implementations must have an explicit
	//constructor in order to declare the
	// RemoteException exception
	public ServiciosImpl()
	throws java.rmi.RemoteException {
		super();
	}

	
	public String iniciarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
	
		logs[indice] = "";
		logs[indice] = "Inicio de sesion por: " + nombre;
		indice++;
			
		return "Bienvenido " + nombre + ". Usted esta conectado al servidor.\n";
	}
	
	public String cerrarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "Cierre de sesion por: " + nombre;
		indice++;
		
		return "Cerrando sesion.\n";
	}
	
	public String listarArchivosEnServidor(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "Listado de archivos por: " + nombre;
		indice++;

		return "Listando archivos en el servidor.\n";
	}
	
	public String subirArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "Subida del archivo: bla.txt por: " + nombre;
		indice++;
	
		return "Subiendo archivo al servidor.\n";
	}
	
	public String bajarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "Descarga del archivo: bla.txt por: " + nombre;
		indice++;
		
		return "Bajando archivo desde el servidor.\n";
	}
			
	public String borrarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "Borrado el archivo: bla.txt por: " + nombre;
		indice++;
		
		return "Borrando archivo en el servidor.\n";
	}
	
	
	
	public void mostrarInformacion(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = nombre + " solicito los comandos disponibles";
		indice++;
		
	}
	
	public void mostrarArchivosLocales(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "El cliente: " + nombre + " solicito listar sus archivos locales";
		indice++;
		
	}
	
	
	public void inicializarLog()
		throws java.rmi.RemoteException {
			indice = 0;
			for (int i = 0; i < 20 ; i++){
				logs[i] = "";
			}
	}

	
	public String entregarLog(String clave)
		throws java.rmi.RemoteException {
			String Log;
			if (clave.equals(claveServidor)) {
				Log = "";
				int j = 0;

				for (int i = indice; i < 20; i++) {
					if (!logs[i].equals("")) {
						j++;
						Log += "\n\t" + (j) + ".- " + logs[i] + ".";
					}
				}
				
				for (int i = 0; i < indice; i++) {
					if (!logs[i].equals("")) {
						j++;
						Log += "\n\t" + (j) + ".- " + logs[i] + ".";
					}
				}

				return Log;
			} else {
				return "Acceso denegado.\n";
			}
				
	}

}
