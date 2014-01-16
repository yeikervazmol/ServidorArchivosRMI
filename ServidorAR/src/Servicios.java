/**
* @author Karen Troiano		09-10855
* @author Yeiker Vazquez	09-10882
* @grupo  15
*
* Archivo: Servicios.java
*
* Descripcion: Interfaz para el programa principal del
* servidor de de archivos.
*/

public interface Servicios
	extends java.rmi.Remote {
	
	public Boolean iniciarSesion(String nombre, String clave, int i)
			throws java.rmi.RemoteException;
	
	public Boolean cerrarSesion(String nombre, String clave)
			throws java.rmi.RemoteException;
	
	public String listarArchivosEnServidor(String nombre, String clave, String nombreArchivo)
	throws java.rmi.RemoteException;
	
	public String subirArchivo(String nombre, String clave, String nombreArchivo, byte[] bytesArchivo)
		throws java.rmi.RemoteException;
	
	public byte[] bajarArchivo(String nombre, String clave, String nombreArchivo)
		throws java.rmi.RemoteException;
	
	public String borrarArchivo(String nombre, String clave, String nombreArchivo)
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
