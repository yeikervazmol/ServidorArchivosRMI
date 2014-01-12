
public interface Servicios
	extends java.rmi.Remote {
	
	public Boolean iniciarSesion(String nombre, String clave)
			throws java.rmi.RemoteException;
	
	public Boolean cerrarSesion(String nombre, String clave)
			throws java.rmi.RemoteException;
	
	public String listarArchivosEnServidor(String nombre, String clave)
	throws java.rmi.RemoteException;
	
	public String subirArchivo(String nombre, String clave)
		throws java.rmi.RemoteException;
	
	public String bajarArchivo(String nombre, String clave)
		throws java.rmi.RemoteException;
	
	public String borrarArchivo(String nombre, String clave)
		throws java.rmi.RemoteException;
	
	public Boolean mostrarInformacion(String nombre, String clave)
			throws java.rmi.RemoteException;
	
	public Boolean mostrarArchivosLocales(String nombre, String clave)
			throws java.rmi.RemoteException;
	
	public void inicializarLog()
		throws java.rmi.RemoteException;
		
	public String entregarLog(String clave)
		throws java.rmi.RemoteException;
}
