import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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
	public static Autenticacion a;
	
	// Implementations must have an explicit
	//constructor in order to declare the
	// RemoteException exception
	public ServiciosImpl(String host, String puerto)
	throws java.rmi.RemoteException {
		super();
		
		try {
			a = (Autenticacion)
					Naming.lookup("rmi://" + host + ":" + puerto + "/Autenticacion");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Boolean iniciarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		if (!(a.autenticarUsuario(nombre, clave))) { 
			return false;
		}
		
		if ( indice >= 20) {
			indice = 0;
		}
	
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Inicio de sesion por: " + nombre;
		indice++;
		
		return true;
	}
	
	public Boolean cerrarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if (!(a.autenticarUsuario(nombre, clave))) { 
			return false;
		}
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Cierre de sesion por: " + nombre;
		indice++;
		
		return true;
	}
	
	public String listarArchivosEnServidor(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		if (!(a.autenticarUsuario(nombre, clave))) { 
			return "false";
		}
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		String path = "."; 
		String respuesta = "";
		
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
 
		for (int i = 0; i < listOfFiles.length; i++){
 
			if (	listOfFiles[i].isFile()){
				files = listOfFiles[i].getName();
				if	( !(files.equals("Servicios.java")
						|| files.equals("Servicios.class")
						|| files.equals("s_rmifs.java")
						|| files.equals("s_rmifs.class")
						|| files.equals("ServiciosImpl.java")
						|| files.equals("ServiciosImpl.class")
						|| files.equals("Autenticacion.java")
						|| files.equals("Autenticacion.class")
						)
					){
					
					respuesta += "\t" + files + "\n";

				}
			}
		}
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Listado de archivos por: " + nombre;
		indice++;

		return respuesta;
	}
	
	public String subirArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Subida del archivo: bla.txt por: " + nombre;
		indice++;
	
		return "Subiendo archivo al servidor.\n";
	}
	
	public String bajarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Descarga del archivo: bla.txt por: " + nombre;
		indice++;
		
		return "Bajando archivo desde el servidor.\n";
	}
			
	public String borrarArchivo(String nombre, String clave)
	throws java.rmi.RemoteException {
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Borrado el archivo: bla.txt por: " + nombre;
		indice++;
		
		return "Borrando archivo en el servidor.\n";
	}
	
	
	
	public Boolean mostrarInformacion(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		if (!(a.autenticarUsuario(nombre, clave))) { 
			return false;
		}
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + nombre + " solicito los comandos disponibles";
		indice++;
		return true;
		
	}
	
	public Boolean mostrarArchivosLocales(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		if (!(a.autenticarUsuario(nombre, clave))) { 
			return false;
		}
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] =  "(" + timeStamp + ") " + nombre + " solicito listar sus archivos locales";
		indice++;
		return true;
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
						if (j < 10){
							Log += "\n\t" + (j) + ".-  " + logs[i] + ".";
						} else {
							Log += "\n\t" + (j) + ".- " + logs[i] + ".";
						}
					}
				}
				
				for (int i = 0; i < indice; i++) {
					if (!logs[i].equals("")) {
						j++;
						if (j < 10){
							Log += "\n\t" + (j) + ".-  " + logs[i] + ".";
						} else {
							Log += "\n\t" + (j) + ".- " + logs[i] + ".";
						}
					}
				}

				return Log;
			} else {
				return "Acceso denegado.\n";
			}
				
	}

}
