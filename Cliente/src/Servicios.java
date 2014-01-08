
public interface Servicios
	extends java.rmi.Remote {
	
	public String listarArchivosEnServidor()
	throws java.rmi.RemoteException;
	
	public String subirArchivo()
		throws java.rmi.RemoteException;
	
	public String bajarArchivo()
		throws java.rmi.RemoteException;
	
	public String borrarArchivo()
		throws java.rmi.RemoteException;
}
